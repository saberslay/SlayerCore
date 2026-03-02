# 🎯 SlayerCore Logger Usage

Use the `Logger` class from `com.saberslay.slayercore` to print **colored console messages** in Java.

---

## 🚀 Static Imports for Convenience

You can use **static imports** to simplify your logging calls:

```java
import static com.saberslay.slayercore.core.logging.Logger.Level.*;
import static com.saberslay.slayercore.core.logging.Logger.log;

public class Main {
    public static void main(String[] args) {
        // ℹ️ Info message
        log(INFO, "This is an informational message!");

        // ⚠️ Warning message
        log(WARNING, "This is a warning message!");

        // ❌ Error message
        log(ERROR, "This is an error message!");
    }
}
```

**Explanation:**

- `import static com.saberslay.slayercore.core.logging.Logger.Level.*;`  
  Allows referencing all log levels (`INFO`, `WARNING`, `ERROR`) directly.

- `import static com.saberslay.slayercore.core.logging.Logger.log;`  
  Allows calling `log(INFO, "message")` without prefixing `Logger.`.

---

## 🎨 Output Example

When run in a terminal supporting ANSI colors:

| Level       | Color | Example Output |
|------------|-------|----------------|
| ℹ️ INFO     | Cyan  | `2026-01-03 14:05:23 [INFO] This is an informational message!` |
| ⚠️ WARNING | Yellow| `2026-01-03 14:05:23 [WARNING] This is a warning message!` |
| ❌ ERROR    | Red   | `2026-01-03 14:05:23 [ERROR] This is an error message!` |

> Each message is automatically colored and reset at the end, preventing color bleeding in the console.

---

## 💡 Tips & Best Practices

- Extend the `Logger` with custom levels like `DEBUG` or `SUCCESS` and assign unique colors.  
- Ensure your terminal supports **ANSI escape codes** (Linux, macOS, Windows Terminal, or modern CMD).  
- Use **static imports** for short and clean logging statements in larger projects.  
- Add emojis or tags to make logs more visually recognizable.  

---

## 🎵 NokiaComposer Example Usage

You can use the `NokiaComposer` from  
`com.saberslay.slayercore.composers` to play **simple Nokia-style monophonic tones** programmatically.

---

### 🚀 Basic Example (Async Playback)

```java
import com.saberslay.slayercore.core.composers.NokiaComposer;

import static com.saberslay.slayercore.core.logging.Logger.Level.*;
import static com.saberslay.slayercore.core.logging.Logger.log;

public class Main {
    public static void main(String[] args) {

        NokiaComposer composer = new NokiaComposer();

        log(INFO, "Playing Nokia-style melody...", data[i]);

        composer.playNotesAsync(
                false,                              // loop
                300,                                // tempo (ms)
                NokiaComposer.NoteLength.SHORT,     // note length
                NokiaComposer.PlayStyle.STACCATO,   // play style
                1, 2, 3, 5, 6, 9, 6, 5, 3, 2, 1      // notes (9 = rest)
        );

        log(INFO, "Melody started asynchronously.", data[i]);
    }
}
```

---

### 📝 Notes Explained

- **Notes (`1–8`)** – Musical notes (C → high C)  
- **`9`** – Rest / pause  
- **Play styles**
  - `STACCATO` → short beeps (classic Nokia)
  - `LEGATO` → smooth tones  
- **Async playback**
  - Runs on a separate thread
  - Does not block your main application

---

```java
log(INFO, "Starting ringtone playback");
composer.playNotesAsync(false, 250, SHORT, STACCATO, 1, 3, 5, 8);
```

---

### 💡 Use Cases

- 🔔 Startup sounds  
- 🎮 Retro-style feedback  
- 📟 Nokia RTTTL-style ringtones  
- 🧪 Audio debugging cues  

---

With `Logger` + `NokiaComposer`, you get **clear visual logs** and **audible feedback**, perfect for SlayerCore tools and utilities.

---

### 🖼️ GUI Framework

- Abstract base class SlayerWindow simplifies window creation:

- Configurable title and size

- Double-buffered rendering via Graphics2D

- Clean lifecycle methods: init() and render()

- Thread-safe launch mechanism

Example
```java
import com.saberslay.slayercore.gui.SlayerWindow;
import java.awt.*;

public class TestApp extends SlayerWindow {

    public TestApp() {
        super("Slayer App", 800, 600);
    }

    @Override
    protected void init() {
        // setup logic
    }

    @Override
    protected void render(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.drawString("Hello SlayerCore", 100, 100);
    }

    public static void main(String[] args) {
        new TestApp().launch();
    }
}
```
---

## 📜 License

This project is licensed under the MIT License.

You are free to use, modify, and distribute this software for personal or commercial purposes, provided that you include the original copyright notice.

For full license details, see the LICENSE file in the repository hosted on [Github](https://github.com/saberslay/SlayerCore)

---

### 🛠️ Contributing

Contributions to the GitHub repository are welcome.

Suggested areas for enhancement include:

- Keyboard and mouse input handling

- Game loop and frame timing system

- Advanced rendering utilities

- UI components, widgets, and sprite management

- Performance optimizations and threading improvements

If you would like to contribute, feel free to open a pull request or submit suggestions.
