/*  for generating custom runtime exception
 *  an exception class must have a constructor with a String argument
 */
package ru.exxo.jutil;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

public class NieGenerator<E extends RuntimeException> {

    private final static String messagePattern = "Method '%s' of class '%s' has not implemented yet";

    private final Class<E> exceptionClass;
    private final String pattern;
    private final Class<?> clazz;

    /**
     *
     * @param exceptionClass a class of generated custom exception
     * @param pattern a pattern of exception message instead of default value
     *                "Method '%s' of class '%s' has not implemented yet"
     * @param clazz a class that possesses the method which should throw an exception
     */
    public NieGenerator(Class<E> exceptionClass, String pattern, Class<?> clazz) {
        this.exceptionClass = exceptionClass;
        this.pattern = pattern;
        this.clazz = clazz;
    }
    /**
     *
     * @param exceptionClass a class of generated custom exception
     * @param clazz a class that possesses the method which should throw an exception
     */
    public NieGenerator(Class<E> exceptionClass, Class<?> clazz) {
        this.exceptionClass = exceptionClass;
        this.pattern = messagePattern;
        this.clazz = clazz;
    }
    /**
     * generates a custom exception with predefined in generator instance parameters
     *
     * @return generated exception
     */
    public E nie() {
        return nie(clazz, pattern, exceptionClass);
    }
    /**
     * for non-static methods
     *
     * @param context for non-static methods it should be 'this'
     * @param pattern a pattern of exception message instead of default value
     *                "Method '%s' of class '%s' has not implemented yet"
     * @param exceptionClass a class of generated custom exception
     * @return generated exception
     */
    public static <E extends RuntimeException> E  nie(Object context, String pattern, Class<E> exceptionClass) {
        return nie(context.getClass(), pattern, exceptionClass);
    }
    /**
     * for static methods
     *
     * @param clazz a class that possesses the method which should throw an exception
     * @param pattern a pattern of exception message instead of default value
     *                "Method '%s' of class '%s' has not implemented yet"
     * @param exceptionClass a class of generated custom exception
     * @return generated exception
     */
    public static <E extends RuntimeException> E nie(Class<?> clazz, String pattern, Class<E> exceptionClass) {
        String msg = getMessage(clazz, pattern);

        try {
            return exceptionClass.getConstructor(String.class).newInstance(msg);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(String.format("Something went wrong while throwing '%s'",
                    exceptionClass.getCanonicalName()));
        }
    }
    /**
     * for non-static methods
     *
     * @param context for non-static methods it should be 'this'
     * @param exceptionClass a class of generated custom exception
     * @return generated exception
     */
    public static <E extends RuntimeException> E  nie(Object context, Class<E> exceptionClass) {
        return nie(context, messagePattern, exceptionClass);
    }
    /**
     * for static methods
     *
     * @param clazz a class that possesses the method which should throw an exception
     * @param exceptionClass a class of generated custom exception
     * @return generated exception
     */
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

