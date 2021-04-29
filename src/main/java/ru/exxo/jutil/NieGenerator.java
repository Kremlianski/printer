package ru.exxo.jutil;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

public class NieGenerator<E extends RuntimeException> {

    private final static String messagePattern = "Method '%s' of class '%s' has not implemented yet";

    private final Class<E> exceptionClass;
    private final String pattern;
    private final Class<?> clazz;

    public NieGenerator(Class<E> exceptionClass, String pattern, Class<?> clazz) {
        this.exceptionClass = exceptionClass;
        this.pattern = pattern;
        this.clazz = clazz;
    }

    public NieGenerator(Class<E> exceptionClass, Class<?> clazz) {
        this.exceptionClass = exceptionClass;
        this.pattern = messagePattern;
        this.clazz = clazz;
    }

    public E nie() {
        return nie(clazz, pattern, exceptionClass);
    }

    public static <E extends RuntimeException> E  nie(Object context, String pattern, Class<E> exceptionClass) {
        return nie(context.getClass(), pattern, exceptionClass);
    }

    public static <E extends RuntimeException> E nie(Class<?> clazz, String pattern, Class<E> exceptionClass) {
        String msg = getMessage(clazz, pattern);

        try {
            return exceptionClass.getConstructor(String.class).newInstance(msg);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(String.format("Something went wrong while throwing '%s'",
                    exceptionClass.getCanonicalName()));
        }


    }

    public static <E extends RuntimeException> E  nie(Object context, Class<E> exceptionClass) {
        return nie(context, messagePattern, exceptionClass);
    }

    public static <E extends RuntimeException> E  nie (Class<?> clazz, Class<E> exceptionClass) {
        return nie(clazz, messagePattern, exceptionClass);
    }

    static String getMessage(Class<?> clazz, String pattern) {

        String className = clazz.getCanonicalName();

        return StackWalker
                .getInstance()
                .walk((Stream<StackWalker.StackFrame> frames) -> {
                    StackWalker.StackFrame frame = frames
                            .dropWhile(f -> !f.getClassName().equals(className))
                            .findFirst()
                            .get();
                    return String.format(pattern, frame.getMethodName(), frame.getClassName());
                });
    }
}

