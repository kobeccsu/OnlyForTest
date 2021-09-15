package com.leizhou.util;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;

class DummyTest {

    @Test
    void shouldGet(){
        MockedStatic<Dummy> dummyMockedStatic = mockStatic(Dummy.class);

        dummyMockedStatic.when(Dummy::foo).thenReturn("Mocked");
        dummyMockedStatic.when(() -> Dummy.foo(anyString())).thenReturn("Mocked String");

        assertEquals("Mocked", Dummy.foo());
        assertEquals("Mocked String", Dummy.foo("abac"));

        dummyMockedStatic.verify(() -> Dummy.foo());
        dummyMockedStatic.verify(() -> Dummy.foo(anyString()));
    }

    @Test
    void testStaticMockWithVerification() {
        try (MockedStatic<Dummy> dummy = mockStatic(Dummy.class)) {
            dummy.when(Dummy::foo).thenReturn("bar");
            assertEquals("bar", Dummy.foo());
            dummy.verify(Dummy::foo);
        }
    }

    @Test
    void testStaticMockWithMoMoreInteractions() {
        try (MockedStatic<Dummy> dummy = mockStatic(Dummy.class)) {
            dummy.when(Dummy::foo).thenReturn("bar");
            assertEquals("bar", Dummy.foo());
            dummy.verify(Dummy::foo);
            dummy.verifyNoMoreInteractions();
        }
    }
}