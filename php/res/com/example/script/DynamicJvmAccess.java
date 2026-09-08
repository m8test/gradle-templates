package com.example.script;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;

public final class DynamicJvmAccess {
    public static final String KIND = "dynamic";

    private DynamicJvmAccess() {
    }

    public enum Sample {
        READY
    }

    public interface Sam {
        int apply(int value);
    }

    public static int callSam(Sam sam, int value) {
        return sam.apply(value);
    }

    public static int add(int left, int right) {
        return left + right;
    }

    public static byte echoByte(byte value) { return value; }
    public static short echoShort(short value) { return value; }
    public static int echoInt(int value) { return value; }
    public static long echoLong(long value) { return value; }
    public static float echoFloat(float value) { return value; }
    public static double echoDouble(double value) { return value; }
    public static char echoChar(char value) { return value; }
    public static boolean echoBoolean(boolean value) { return value; }

    public static Byte echoByteBoxed(Byte value) { return value; }
    public static Short echoShortBoxed(Short value) { return value; }
    public static Integer echoIntBoxed(Integer value) { return value; }
    public static Long echoLongBoxed(Long value) { return value; }
    public static Float echoFloatBoxed(Float value) { return value; }
    public static Double echoDoubleBoxed(Double value) { return value; }
    public static Character echoCharBoxed(Character value) { return value; }
    public static Boolean echoBooleanBoxed(Boolean value) { return value; }

    public static boolean negate(boolean value) {
        return !value;
    }

    public static double scale(double value, double factor) {
        return value * factor;
    }

    public static String join(String left, String right) {
        return left + ":" + right;
    }

    public static Integer boxed(int value) {
        return value;
    }

    public static Object identity(Object value) { return value; }

    public static String nullable(String value) { return value == null ? "NULL" : value; }

    public static Date date() { return new Date(0L); }

    public static Calendar calendar() { return Calendar.getInstance(); }

    public static BigInteger bigInteger() { return new BigInteger("12345678901234567890"); }

    public static BigDecimal bigDecimal() { return new BigDecimal("12.345"); }

    public static int byteArrayLength(byte[] values) { return values.length; }

    public static int objectArrayLength(Object[] values) { return values.length; }

    public static String className(Class value) { return value.getName(); }

    public static int sumInts(int[] values) {
        int result = 0;
        for (int value : values) result += value;
        return result;
    }

    public static long sumLongs(long[] values) {
        long result = 0;
        for (long value : values) result += value;
        return result;
    }

    public static String joinStrings(String[] values) {
        return values[0] + ":" + values[1];
    }

    public static int listSize(List values) { return values.size(); }

    public static int setSize(Set values) { return values.size(); }

    public static Object mapValue(Map values, String key) { return values.get(key); }

    public static String varargs(String... values) {
        return values[0] + ":" + values[1];
    }

    public static int[] ints(int first, int second) {
        return new int[]{first, second};
    }

    public static String[] strings(String first, String second) {
        return new String[]{first, second};
    }

    public static List<String> list(String first, String second) {
        return Arrays.asList(first, second);
    }

    public static Map<String, Integer> map(String key, int value) {
        Map<String, Integer> result = new LinkedHashMap<String, Integer>();
        result.put(key, value);
        return result;
    }

    public static Sample enumValue() {
        return Sample.READY;
    }
}
