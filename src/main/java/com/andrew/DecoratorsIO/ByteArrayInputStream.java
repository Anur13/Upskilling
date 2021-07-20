package com.andrew.DecoratorsIO;


import java.io.IOException;
import java.io.InputStream;

public class ByteArrayInputStream extends InputStream {
    byte[] buffer;
    int count;


    public ByteArrayInputStream(byte[] buffer) {
        this.buffer = buffer;
    }

    @Override
    public int read(byte[] b) throws IOException {
        if (checkCapacity(b.length)) {
            return -1;
        }

        System.arraycopy(buffer, count, b, 0, buffer.length);
        count += buffer.length;
        return buffer.length;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        System.out.println();
        if (checkCapacity(b.length)) {
            return -1;
        }
        System.arraycopy(buffer, count, b, off, len);
        count += len;
        return len;
    }

    @Override
    public void close() throws IOException {
        super.close();
    }

    @Override
    public int read() throws IOException {
        if (count == buffer.length) {
            return -1;
        }
        return buffer[count++];
    }

    private boolean checkCapacity(int elements) {
        return buffer.length - count < elements;
    }
}
