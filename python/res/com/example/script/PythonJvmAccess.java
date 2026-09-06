package com.example.script;

import java.util.Arrays;
import java.util.List;

/** JVM interop fixture used by the Python template. */
public class PythonJvmAccess {
    public static final String STATIC_FIELD = "static field from Java";
    public final String instanceField = "instance field from Java";

    public interface Callback {
        String call(String value, int count);
    }

    public static String message() {
        return "PythonJvmAccess loaded";
    }

    public static int sumInt(int left, int right) {
        return left + right;
    }

    public static long sumLong(long left, long right) {
        return left + right;
    }

    public static double sumNumbers(int integer, long longValue, float floatValue, double doubleValue) {
        return integer + longValue + floatValue + doubleValue;
    }

    public static String describe(String text, boolean enabled, char marker) {
        return text + ":" + enabled + ":" + marker;
    }

    public static int arrayLength(int[] values) {
        return values.length;
    }

    public static String joinStrings(String[] values) {
        return String.join(",", values);
    }

    public static String listSummary(List<String> values) {
        return values.size() + ":" + values.get(0) + ":" + values.get(values.size() - 1);
    }

    public static String callback(Callback callback) {
        return callback.call("callback", 7);
    }

    public String instanceMessage() {
        return "Hello Python from Java";
    }

    public String instanceValues(String prefix, int count, boolean enabled) {
        return prefix + ":" + count + ":" + enabled;
    }

    public String instanceArray(int[] values) {
        return Arrays.toString(values);
    }
}
