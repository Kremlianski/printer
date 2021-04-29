package ru.exxo.jutil;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static ru.exxo.jutil.UoeGenerator.*;

class UoeGeneratorTest {

    final Some obj = new Some();


    @Test
    void staticMethodWithThis() {
        Exception exception = Assertions.assertThrows(UnsupportedOperationException.class, obj::method);
        assertEquals(exception.getMessage(), "Method 'method' of class 'ru.exxo.jutil.Some' has not implemented yet");
    }

    @Test
    void staticMethodWithClass() {
        Exception exception = Assertions.assertThrows(UnsupportedOperationException.class, obj::method1);
        assertEquals(exception.getMessage(), "Method 'method1' of class 'ru.exxo.jutil.Some' has not implemented yet");
    }

    @Test
    void staticMethodWithThisAndPattern() {
        Exception exception = Assertions.assertThrows(UnsupportedOperationException.class, () -> obj.method2(""));
        assertEquals(exception.getMessage(), "method 'method2' is wrong");
    }

    @Test
    void staticMethodWithClassAndPattern() {
        Exception exception = Assertions.assertThrows(UnsupportedOperationException.class, () -> obj.method3(""));
        assertEquals(exception.getMessage(), "method 'method3' is wrong");
    }

    @Test
    void methodWithGenerator() {
        Exception exception = Assertions.assertThrows(UnsupportedOperationException.class, obj::method4);
        assertEquals(exception.getMessage(), "Method 'method4' of class 'ru.exxo.jutil.Some' has not implemented yet");
    }

    @Test
    void methodWithGeneratorAndPattern() {
        Exception exception = Assertions.assertThrows(UnsupportedOperationException.class, obj::method5);
        assertEquals(exception.getMessage(), "method 'method5' is wrong");
    }
}

class Some {
    UoeGenerator gen = new UoeGenerator(Some.class);
    UoeGenerator gen1 = new UoeGenerator("method '%s' is wrong", Some.class);

    void method() {
        throw uoe(this);
    }

    void method1() {
        throw uoe(Some.class);
    }

    void method2(String arg) {
        throw uoe(this, "method '%s' is wrong");
    }

    void method3(String arg) {
        throw uoe(Some.class, "method '%s' is wrong");
    }

    void method4(){
        throw gen.uoe();
    }

    void method5(){
        throw gen1.uoe();
    }

}