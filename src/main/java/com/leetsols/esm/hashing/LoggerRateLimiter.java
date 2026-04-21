package com.leetsols.esm.hashing;

import java.util.HashMap;

public class LoggerRateLimiter {
    private final HashMap<String, Integer> map;

    public LoggerRateLimiter() {
        map = new HashMap<>();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        if (map.containsKey(message)) {
            var curr = map.get(message) + 10;
            if (timestamp < curr) {
                return false;
            } else {
                map.put(message, timestamp);
            }
        }
        map.put(message, timestamp);
        return true;
    }
}
