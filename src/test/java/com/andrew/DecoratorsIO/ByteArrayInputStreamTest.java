package com.andrew.DecoratorsIO;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

//import java.io.ByteArrayInputStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ByteArrayInputStreamTest {
    String content = "Hello";
    byte[] bytesOfContent = content.getBytes();
    byte[] test = new byte[5];
    int bytesRead;
    ByteArrayInputStream byteArrayInputStream
            = new ByteArrayInputStream(bytesOfContent);

    @Test
    @DisplayName("Test Read With Byte Array In Class Instance")
    public void testReadWithByteArrayInClassInstance() throws IOException {
        assertEquals('H', (char) byteArrayInputStream.read());
        assertEquals('e', (char) byteArrayInputStream.read());
        assertEquals('l', (char) byteArrayInputStream.read());
        assertEquals('l', (char) byteArrayInputStream.read());
        assertEquals('o', (char) byteArrayInputStream.read());
        assertEquals(-1, byteArrayInputStream.read());
    }

    @Test
    @DisplayName("Test Read With Array Copy")
    public void testReadWithByteArrayAsParameter() throws IOException {
        bytesRead = byteArrayInputStream.read(test);
        assertEquals(5, bytesRead);
        assertArrayEquals(bytesOfContent, test);

    }

    @Test
    @DisplayName("Test Read With Byte Array And Offset and Length")
    public void testReadWithByteArrayAndOffsetAndLength() throws IOException {
        bytesRead = byteArrayInputStream.read(test, 0, 3);
        for (int i = 0; i < 3; i++) {
            assertEquals(bytesOfContent[i], test[i]);
        }
        assertEquals(3, bytesRead);

        bytesRead = byteArrayInputStream.read(test, 3, 5);
        assertEquals(-1, -1);

        bytesRead = byteArrayInputStream.read(test, 3, 1);

        System.out.println(Arrays.toString(test));
        assertEquals('l', (char) test[2]);
    }
}

