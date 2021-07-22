package com.andrew.DecoratorsIO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class ByteArrayOutputStream extends OutputStream {
    byte[] buffer;
    int count;


    public ByteArrayOutputStream() {
        buffer = new byte[32];
    }

    public ByteArrayOutputStream(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Negative initial size: " + size);
        } else {
            buffer = new byte[size];
        }
    }

    private void checkAndAdjustSize(int addedItems) {
        if (count + addedItems > buffer.length) {
            buffer = Arrays.copyOf(buffer, (int) Math.floor(buffer.length * 1.5));
        }
    }

    @Override
    public void write(int b) throws IOException {
        checkAndAdjustSize(b);
        buffer[count++] = (byte) b;
    }

    @Override
    public void write(byte[] b) throws IOException {
        checkAndAdjustSize(b.length);
        System.arraycopy(b, 0, buffer, count, b.length);
        count += b.length;
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        checkAndAdjustSize(len);
        System.arraycopy(b, off, buffer, count, len);
        count += len;
    }

    @Override
    public void flush() throws IOException {
        super.flush();
    }

    @Override
    public void close() throws IOException {
        super.close();
    }
}
