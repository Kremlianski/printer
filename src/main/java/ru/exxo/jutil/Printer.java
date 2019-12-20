package ru.exxo.jutil;

public interface Printer {
    static void println(String str) {
        System.out.println(str);
    }
    static void print(String str) {
        System.out.print(str);
    }
}
