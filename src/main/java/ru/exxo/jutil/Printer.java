package ru.exxo.jutil;

public interface Printer {

    static <T> void print(T t) {
        System.out.print(t);
    }

    static <T> void println(T t) {
        System.out.println(t);
    }
}
