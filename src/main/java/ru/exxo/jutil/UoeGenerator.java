package ru.exxo.jutil;

public class UoeGenerator {

    private final static String messagePattern = "Method '%s' of class '%s' has not implemented yet";

    private final String pattern;
    private final Class<?> clazz;

    /**
     *
     * @param pattern a pattern of exception message instead of default value
     *                "Method '%s' of class '%s' has not implemented yet"
     * @param contextClass a class that possesses the method which should throw an exception
     */
    public UoeGenerator(String pattern, Class<?> contextClass) {
        this.pattern = pattern;
        this.clazz = contextClass;
    }

    /**
     *
     * @param contextClass a class that possesses the method which should throw an exception
     */
    public UoeGenerator(Class<?> contextClass) {
        this.clazz = contextClass;
        this.pattern = messagePattern;
    }

    /**
     * generates UnsupportedOperationException with predefined in generator instance parameters
     *
     * @return generated exception
     */
    public UnsupportedOperationException uoe() {
        return uoe(clazz, pattern);
    }

    /**
     * for non-static methods
     *
     * @param context for non-static methods it should be 'this'
     * @param pattern a pattern of exception message instead of default value
     *                "Method '%s' of class '%s' has not implemented yet"
     * @return generated exception
     */
    public static UnsupportedOperationException uoe(Object context, String pattern) {
        return uoe(context.getClass(), pattern);
    }

    /**
     * for static methods
     *
     * @param clazz a class that possesses the method which should throw an exception
     * @param pattern a pattern of exception message instead of default value
     *                "Method '%s' of class '%s' has not implemented yet"
     * @return generated exception
     */
    public static UnsupportedOperationException uoe(Class<?> clazz, String pattern) {

        String msg = NieGenerator.getMessage(clazz, pattern);

        return new UnsupportedOperationException(msg);
    }

    /**
     * for non-static methods
     *
     * @param context for non-static methods it should be 'this'
     * @return generated exception
     */
    public static UnsupportedOperationException uoe(Object context) {
        return uoe(context, messagePattern);
    }

    /**
     * for static methods
     *
     * @param clazz class that possesses the method which should throw an exception
     * @return generated exception
     */
    public static UnsupportedOperationException uoe (Class<?> clazz) {
        return uoe(clazz, messagePattern);
    }
}
