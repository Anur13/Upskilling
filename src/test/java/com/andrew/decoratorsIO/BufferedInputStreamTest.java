package com.andrew.decoratorsIO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BufferedInputStreamTest {
    String mock = "Buffer test";
    BufferedInputStream test;
    {
        try {
            test = new BufferedInputStream(new java.io.ByteArrayInputStream(mock.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testReadNoParams() throws IOException {
        assertEquals('B', test.read());
        assertEquals('u', test.read());
        assertEquals('f', test.read());
        assertEquals('f', test.read());
        assertEquals('e', test.read());
        assertEquals('r', test.read());
        assertEquals(' ', test.read());
        assertEquals('t', test.read());
        assertEquals('e', test.read());
        assertEquals('s', test.read());
        assertEquals('t', test.read());
    }

    @Test
    void checkClose() {
        //QUESTION:  Каким образом тестировать приватные методы и поля?
        // Через рефлексию?
    }

    @Test
    @DisplayName("Check With Array Offset Length")
    void checkWithArrayOffsetLen() throws IOException {
        byte[] array = new byte[5];
        byte[] result = {66, 117, 0, 0, 0};
        assertEquals(2, test.read(array, 0, 2));
        assertArrayEquals(result, array);
    }
}