package com.uptc.is.util;

import java.util.HashMap;
import java.util.Map;

public class IdGenerator {

    private static final Map<String, Integer> counters = new HashMap<>();

    public static String generateId(String prefix) {
        int current = counters.getOrDefault(prefix, 1);
        counters.put(prefix, current + 1);
        return prefix + current;
    }

    public static void updateCounter(String prefix, int newValue) {
        counters.put(prefix, newValue);
    }

    public static int getCounter(String prefix) {
        return counters.getOrDefault(prefix, 1);
    }

}

