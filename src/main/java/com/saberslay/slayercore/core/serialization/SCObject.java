package com.saberslay.slayercore.core.serialization;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

import static com.saberslay.slayercore.core.serialization.SerializationUtils.*;

import java.util.ArrayList;
import java.util.List;

public class SCObject extends SCBase {

	public static final byte CONTAINER_TYPE = ContainerType.OBJECT;

	private short fieldCount;
	public List<SCField> fields = new ArrayList<>();

	private short stringCount;
	public List<SCString> strings = new ArrayList<>();

	private short arrayCount;
	public List<SCArray> arrays = new ArrayList<>();

	private SCObject() {}

	public SCObject(String name) {
		setName(name);
	}

	// --------------------------------------------------
	// ADD METHODS
	// --------------------------------------------------

	public void addField(SCField field) {
		fields.add(field);
		fieldCount = (short) fields.size();
	}

	public void addString(SCString string) {
		strings.add(string);
		stringCount = (short) strings.size();
	}

	public void addArray(SCArray array) {
		arrays.add(array);
		arrayCount = (short) arrays.size();
	}

	// --------------------------------------------------
	// FIXED SIZE CALCULATION
	// --------------------------------------------------

	@Override
	public int getSize() {

		int total = 0;

		// container type
		total += 1;

		// nameLength + name bytes
		total += 2 + name.length;

		// size field
		total += 4;

		// fieldCount
		total += 2;

		for (SCField f : fields)
			total += f.getSize();

		// stringCount
		total += 2;

		for (SCString s : strings)
			total += s.getSize();

		// arrayCount
		total += 2;

		for (SCArray a : arrays)
			total += a.getSize();

		return total;
	}

	// --------------------------------------------------
	// SERIALIZATION
	// --------------------------------------------------

	public int getBytes(byte[] dest, int pointer) {

		int size = getSize(); // ALWAYS compute fresh

		pointer = writeBytes(dest, pointer, CONTAINER_TYPE);
		pointer = writeBytes(dest, pointer, nameLength);
		pointer = writeBytes(dest, pointer, name);
		pointer = writeBytes(dest, pointer, size);

		pointer = writeBytes(dest, pointer, fieldCount);
		for (SCField field : fields)
			pointer = field.getBytes(dest, pointer);

		pointer = writeBytes(dest, pointer, stringCount);
		for (SCString string : strings)
			pointer = string.getBytes(dest, pointer);

		pointer = writeBytes(dest, pointer, arrayCount);
		for (SCArray array : arrays)
			pointer = array.getBytes(dest, pointer);

		return pointer;
	}

	// --------------------------------------------------
	// DESERIALIZATION
	// --------------------------------------------------

	public static SCObject Deserialize(byte[] data, int pointer) {

		byte containerType = data[pointer++];
		assert(containerType == CONTAINER_TYPE);

		SCObject result = new SCObject();

		result.nameLength = readShort(data, pointer);
		pointer += 2;

		result.name = readString(data, pointer, result.nameLength).getBytes();
		pointer += result.nameLength;

		result.size = readInt(data, pointer);
		pointer += 4;

		result.fieldCount = readShort(data, pointer);
		pointer += 2;

		for (int i = 0; i < result.fieldCount; i++) {
			SCField field = SCField.Deserialize(data, pointer);
			result.fields.add(field);
			pointer += field.getSize();
		}

		result.stringCount = readShort(data, pointer);
		pointer += 2;

		for (int i = 0; i < result.stringCount; i++) {
			SCString string = SCString.Deserialize(data, pointer);
			result.strings.add(string);
			pointer += string.getSize();
		}

		result.arrayCount = readShort(data, pointer);
		pointer += 2;

		for (int i = 0; i < result.arrayCount; i++) {
			SCArray array = SCArray.Deserialize(data, pointer);
			result.arrays.add(array);
			pointer += array.getSize();
		}

		return result;
	}
}