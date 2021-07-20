package com.andrew.DecoratorsIO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Arrays;

class ByteArrayOutputStreamTest {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    String content = "Hello";
    byte[] bytesOfContent = content.getBytes();

    @Test
    @DisplayName("Test Read With One Byte")
    public void testReadWithOneByte() throws IOException {
        byteArrayOutputStream.write(4);
        assertEquals(4, byteArrayOutputStream.buffer[0]);

        byteArrayOutputStream.write(3);
        assertEquals(3, byteArrayOutputStream.buffer[1]);

        byteArrayOutputStream.write(2);
        assertEquals(2, byteArrayOutputStream.buffer[2]);

    }

    @Test
    @DisplayName("Test Read With Byte Array")
    public void testReadWithByteArray() throws IOException {
        byteArrayOutputStream.write(bytesOfContent);
        for (int i = 0; i < bytesOfContent.length; i++) {
            assertEquals(bytesOfContent[i], byteArrayOutputStream.buffer[i]);
        }
        byteArrayOutputStream.write(bytesOfContent);
        for (int i = 0; i < bytesOfContent.length; i++) {
            assertEquals(bytesOfContent[i], byteArrayOutputStream.buffer[i + bytesOfContent.length]);
        }
    }

    @Test
    @DisplayName("Test Read With Byte Array With Offset And Length")
    public void testReadWithByteArrayWithOffSetAndLength() throws IOException {
        byteArrayOutputStream.write(bytesOfContent, 1, 1);
        System.out.println(Arrays.toString(byteArrayOutputStream.buffer));
        assertEquals(bytesOfContent[1], byteArrayOutputStream.buffer[0]);
    }
}
