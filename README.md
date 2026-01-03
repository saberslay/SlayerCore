# 🎯 SlayerCore Logger Usage

Use the `Logger` class from `com.saberslay.slayerCore` to print **colored console messages** in Java.

---

## 🚀 Static Imports for Convenience

You can use **static imports** to simplify your logging calls:

```java
import static com.saberslay.slayerCore.Logger.Level.*;
import static com.saberslay.slayerCore.Logger.log;

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

- `import static com.saberslay.slayerCore.Logger.Level.*;`  
  Allows referencing all log levels (`INFO`, `WARNING`, `ERROR`) directly.

- `import static com.saberslay.slayerCore.Logger.log;`  
  Allows calling `log(INFO, "message")` without prefixing `Logger.`.

---

## 🔹 Using Convenience Methods

Instead of static imports, you can call **explicit methods**:

```java
import com.saberslay.slayerCore.Logger;

public class Main {
    public static void main(String[] args) {
        Logger.info("This is an info message!");       // ℹ️ Info
        Logger.warn("This is a warning message!");     // ⚠️ Warning
        Logger.error("This is an error message!");     // ❌ Error
    }
}
```

✅ This is helpful for clarity or when using multiple loggers.

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

With this setup, your logs will be **clear, color-coded, and professional-looking**, making it easier to debug or monitor your applications.

