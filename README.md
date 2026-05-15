# SlayerCore

**Foundational Serialization, Crypto, Networking, Logging, and Platform Utilities**  
**Target Runtime:** Java 21 (LTS)

SlayerCore is a low-level foundational library providing binary serialization, secure networking utilities, cryptographic tools, logging, platform helpers, and shared core modules for applications. It standardises common functionality and eliminates duplicated engine code across projects.

---

## 1. Logger Module

**Package:** `com.saberslay.slayercore.core.logging`

The Logger provides timestamped, coloured console output with simple static methods.

### Basic Usage

```java
import com.saberslay.slayercore.core.logging.Logger;

Logger.log(Logger.Level.INFO, "Application started");
Logger.log(Logger.Level.WARNING, "Low memory");
Logger.log(Logger.Level.ERROR, "Unhandled exception");
```

### Static Import Usage

```java
import static com.saberslay.slayercore.core.logging.Logger.Level.*;
import static com.saberslay.slayercore.core.logging.Logger.log;

log(INFO, "Info message");
log(WARNING, "Warning message");
log(ERROR, "Error message");
```

---

## 2. NokiaComposer Module

**Package:** `com.saberslay.slayercore.core.composers`

Provides simple monophonic tone playback similar to classic Nokia devices.

### Example

```java
import com.saberslay.slayercore.core.composers.NokiaComposer;

NokiaComposer composer = new NokiaComposer();
composer.setVolume(0.8f);

composer.playNotesAsync(
        false,
        300,
        NokiaComposer.NoteLength.SHORT,
        NokiaComposer.PlayStyle.STACCATO,
        1, 2, 3, 5, 6, 9, 6, 5, 3, 2, 1
);
```

**Notes:**
- `1–8` represent tones
- `9` represents a rest
- Playback is asynchronous and non-blocking

---

## 3. Platform Module

**Package:** `com.saberslay.slayercore.core.platform`

Provides OS-aware directory helpers for storing application data.

### Example

```java
import com.saberslay.slayercore.core.platform.Platform;

Path dataDir = Platform.ensureDir(
        Platform.appDataDir("SlayerJournal")
);
```

Automatically detects **Windows**, **macOS**, and **Linux**.

---

## 4. Crypto Module

**Package:** `com.saberslay.slayercore.core.crypto`

Includes **AES-256 CTR encryption** with **HMAC-SHA256 authentication** and secure database channels.

### StrongCustomCipher

```java
StrongCustomCipher cipher = new StrongCustomCipher(masterKey);

byte[] encrypted = cipher.encrypt(data);
byte[] decrypted = cipher.decrypt(encrypted);
```

### SecureSCDatabaseChannel

```java
SecureSCDatabaseChannel channel =
        new SecureSCDatabaseChannel(masterKey, listener);

byte[] packet = channel.encode(database);
channel.decode(packet);
```

---

## 5. Serialization Module

**Package:** `com.saberslay.slayercore.core.serialization`

SlayerCore’s binary serialization system is built around:

- `SCDatabase`
- `SCObject`
- `SCField`
- `SCString`
- `SCInt`
- `SCByteArray`
- And additional field types

### Creating a Database

```java
SCDatabase db = new SCDatabase("ExampleDB");

SCObject user = new SCObject("User1");
user.addString(SCString.Create("name", "test_user"));
user.addInt(SCInt.Create("score", 42));

db.addObject(user);
```

### Serializing

```java
byte[] data = db.serialize();
```

### Deserializing

```java
SCDatabase db = SCDatabase.Deserialize(data);
```

---

## 6. AppInfo Module

**Package:** `com.saberslay.slayercore.core.system`

Provides access to the application version from the JAR manifest.

### Maven Configuration

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-jar-plugin</artifactId>
    <version>3.3.0</version>
    <configuration>
        <archive>
            <manifest>
                <addDefaultImplementationEntries>true</addDefaultImplementationEntries>
            </manifest>
        </archive>
    </configuration>
</plugin>
```

### Usage

```java
import com.saberslay.slayercore.core.system.AppInfo;

String version = AppInfo.getVersion();
```

---

## 7. Networking Modules

**Package:** `com.saberslay.slayercore.core.net`

SlayerCore includes secure UDP networking primitives:

- `SecureUDPClientBase`
- `SecureUDPServerBase`
- `SecureSCDatabaseServer`

These provide encrypted transport for serialized data.

---

## 8. License

This project is licensed under the **MIT License**.

You may use, modify, and distribute the software with attribution.

See the `LICENSE` file for full details.

---

## 9. Contributing

Contributions are welcome.

Open a pull request or submit suggestions on GitHub.

---

## Features Overview

- Binary serialization system
- AES-256 CTR encryption + HMAC authentication
- Secure UDP networking
- Timestamped coloured logging
- Cross-platform application data directories
- Nokia-style tone composer
- JAR manifest version retrieval
- Java 21 LTS support
- MIT licensed
