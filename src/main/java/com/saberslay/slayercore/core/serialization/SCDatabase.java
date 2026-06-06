package com.saberslay.slayercore.core.serialization;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

import static com.saberslay.slayercore.core.serialization.SerializationUtils.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SCDatabase extends SCBase {

	public static final byte[] HEADER = "SCDB".getBytes();
	public static final short VERSION = 0x0100;
	public static final byte CONTAINER_TYPE = ContainerType.DATABASE;

	private short objectCount;
	public List<SCObject> objects = new ArrayList<>();

	private SCDatabase() {}

	public SCDatabase(String name) {
		setName(name);
	}

	// --------------------------------------------------
	// ADD OBJECT
	// --------------------------------------------------

	public void addObject(SCObject object) {
		objects.add(object);
		objectCount = (short) objects.size();
	}

	public SCObject findObject(String name) {
		for (SCObject object : objects) {
			if (object.getName().equals(name)) {
				return object;
			}
		}
		return null;
	}


	// --------------------------------------------------
	// FIXED DYNAMIC SIZE CALCULATION
	// --------------------------------------------------

	@Override
	public int getSize() {
		int total = 0;

		// HEADER + VERSION
		total += HEADER.length; // "SCDB"
		total += 2;             // VERSION

		// container type
		total += 1;

		// nameLength + name bytes
		total += 2 + name.length;

		// size field
		total += 4;

		// objectCount
		total += 2;

		// objects
		for (SCObject o : objects) {
			total += o.getSize();
		}

		return total;
	}

	// --------------------------------------------------
	// SERIALIZATION
	// --------------------------------------------------

	public int getBytes(byte[] dest, int pointer) {

		int size = getSize(); // ALWAYS recompute

		pointer = writeBytes(dest, pointer, HEADER);
		pointer = writeBytes(dest, pointer, VERSION);
		pointer = writeBytes(dest, pointer, CONTAINER_TYPE);
		pointer = writeBytes(dest, pointer, nameLength);
		pointer = writeBytes(dest, pointer, name);
		pointer = writeBytes(dest, pointer, size);

		pointer = writeBytes(dest, pointer, objectCount);

		for (SCObject object : objects) {
			pointer = object.getBytes(dest, pointer);
		}

		return pointer;
	}

	// --------------------------------------------------
	// DESERIALIZATION
	// --------------------------------------------------

	public static SCDatabase Deserialize(byte[] data) {
		int pointer = 0;

		assert(readString(data, pointer, HEADER.length).equals(HEADER));
		pointer += HEADER.length;

		if (readShort(data, pointer) != VERSION) {
			System.err.println("Invalid SCDB version!");
			return null;
		}
		pointer += 2;

		byte containerType = readByte(data, pointer++);
		assert(containerType == CONTAINER_TYPE);

		SCDatabase result = new SCDatabase();

		result.nameLength = readShort(data, pointer);
		pointer += 2;

		result.name = readString(data, pointer, result.nameLength).getBytes();
		pointer += result.nameLength;

		result.size = readInt(data, pointer);
		pointer += 4;

		result.objectCount = readShort(data, pointer);
		pointer += 2;

		for (int i = 0; i < result.objectCount; i++) {
			SCObject object = SCObject.Deserialize(data, pointer);
			result.objects.add(object);
			pointer += object.getSize();
		}

		return result;
	}

	// --------------------------------------------------
	// FILE IO
	// --------------------------------------------------

	public static SCDatabase DeserializeFromFile(String path) {
		byte[] buffer = null;
		try {
			BufferedInputStream stream = new BufferedInputStream(new FileInputStream(path));
			buffer = new byte[stream.available()];
			stream.read(buffer);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return Deserialize(buffer);
	}

	public void serializeToFile(String path) {
		byte[] data = new byte[getSize()];
		getBytes(data, 0);
		try {
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(path));
			stream.write(data);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public byte[] serialize() {
		byte[] data = new byte[getSize()];
		getBytes(data, 0);
		return data;
	}
}