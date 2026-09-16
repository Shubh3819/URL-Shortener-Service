package com.example.urlshortener.util;

public final class Base62Encoder {

    private static final char[] CHARACTERS =
            "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    private Base62Encoder() {}

    public static String encode(long number) {
        if (number == 0) return "0";

        StringBuilder result = new StringBuilder();

        while (number > 0) {
            result.append(CHARACTERS[(int) (number % 62)]);
            number /= 62;
        }

        return result.reverse().toString();
    }
}
