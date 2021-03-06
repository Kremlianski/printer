package ru.exxo.jutil;

import java.util.Locale;

public interface Printer {

    static <T> void print(T t) {
        System.out.print(t);
    }

    static <T> void println(T t) {
        System.out.println(t);
    }

    static void printf(String str, Object... objects) {
        System.out.printf(str, objects);
    }

    static void printf(Locale locale, String str, Object... objects) {
        System.out.printf(locale, str, objects);
    }
}
