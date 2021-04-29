package ru.exxo.jutil;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static ru.exxo.jutil.NieGenerator.*;

class NieGeneratorTest {

    final Some1 obj = new Some1();

    @Test
    void staticMethodWithThis() {
        Exception exception = Assertions.assertThrows(NotImplementedException.class, obj::method);
        assertEquals(exception.getMessage(), "Method 'method' of class 'ru.exxo.jutil.Some1' has not implemented yet");
    }

    @Test
    void staticMethodWithClass() {
        Exception exception = Assertions.assertThrows(NotImplementedException.class, obj::method1);
        assertEquals(exception.getMessage(), "Method 'method1' of class 'ru.exxo.jutil.Some1' has not implemented yet");
    }

    @Test
    void staticMethodThisClassAndPattern() {
        Exception exception = Assertions.assertThrows(NotImplementedException.class, obj::method2);
        assertEquals(exception.getMessage(), "method 'method2' is wrong");
    }

    @Test
    void staticMethodWithClassAndPattern() {
        Exception exception = Assertions.assertThrows(NotImplementedException.class, obj::method3);
        assertEquals(exception.getMessage(), "method 'method3' is wrong");
    }

    @Test
    void methodWithGenerator() {
        Exception exception = Assertions.assertThrows(NotImplementedException.class, obj::method4);
        assertEquals(exception.getMessage(), "Method 'method4' of class 'ru.exxo.jutil.Some1' has not implemented yet");
    }

    @Test
    void methodWithGeneratorAndPattern() {
        Exception exception = Assertions.assertThrows(NotImplementedException.class, obj::method5);
        assertEquals(exception.getMessage(), "method 'method5' is wrong");
    }

}

class Some1 {
    NieGenerator<NotImplementedException> gen = new NieGenerator<>(NotImplementedException.class,Some1.class);
    NieGenerator<NotImplementedException> gen1 = new NieGenerator<>(NotImplementedException.class, "method '%s' is wrong", Some1.class);

    void method() {
        throw nie(this, NotImplementedException.class);
    }

    void method1() {
        throw nie(Some1.class, NotImplementedException.class);
    }

    void method2() {
        throw nie(this, "method '%s' is wrong", NotImplementedException.class);
    }

    void method3() {
        throw nie(Some1.class, "method '%s' is wrong", NotImplementedException.class);
    }

    void method4(){
        throw gen.nie();
    }

    void method5(){
        throw gen1.nie();
    }

}

class NotImplementedException extends RuntimeException {

    public NotImplementedException(String msg) {
        super(msg);
    }
}