package ecommerce_engine;

import java.time.LocalDateTime;
import java.util.*;

public class AuditLogger {
    private static final List<String> logs = new ArrayList<>();

    public static void log(String message) {
        logs.add(LocalDateTime.now() + " | " + message);
    }

    public static void showLogs() {
        if (logs.isEmpty()) {
            System.out.println("No logs available.");
            return;
        }

        for (String log : logs) {
            System.out.println(log);
        }
    }
}