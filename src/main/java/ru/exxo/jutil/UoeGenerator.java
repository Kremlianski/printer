package ru.exxo.jutil;

public class UoeGenerator {

    private final static String messagePattern = "Method '%s' of class '%s' has not implemented yet";

    private final String pattern;
    private final Class<?> clazz;

    public UoeGenerator(String pattern, Class<?> contextClass) {
        this.pattern = pattern;
        this.clazz = contextClass;
    }

    public UoeGenerator(Class<?> contextClass) {
        this.clazz = contextClass;
        this.pattern = messagePattern;
    }


    public UnsupportedOperationException uoe() {
        return uoe(clazz, pattern);
    }

    public static UnsupportedOperationException uoe(Object context, String pattern) {
        return uoe(context.getClass(), pattern);
    }

    public static UnsupportedOperationException uoe(Class<?> clazz, String pattern) {
        String className = clazz.getCanonicalName();

        String msg = NieGenerator.getMessage(clazz, pattern);

        return new UnsupportedOperationException(msg);
    }

    public static UnsupportedOperationException uoe(Object context) {
        return uoe(context, messagePattern);
    }

    public static UnsupportedOperationException uoe (Class<?> clazz) {
        return uoe(clazz, messagePattern);
    }
}
