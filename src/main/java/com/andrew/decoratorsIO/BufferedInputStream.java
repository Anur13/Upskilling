package com.andrew.decoratorsIO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

class BufferedInputStream extends InputStream {
    private byte[] buffer;
    private final int DEFAULT_BUFFER_SIZE = 8192;
    private InputStream inputStream;

    public void BufferedInputStream(InputStream inputStream, int size) throws IOException {
        this.inputStream = inputStream;
        buffer = Arrays.copyOf(inputStream.readAllBytes(), size);
    }

    public void BufferedInputStream(InputStream inputStream) throws IOException {
        this.BufferedInputStream(inputStream, DEFAULT_BUFFER_SIZE);
    }


    @Override
    public int read(byte[] b) throws IOException {
        return super.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return super.read(b, off, len);
    }

    @Override
    public void close() throws IOException {
        super.close();
    }

    @Override
    public int read() throws IOException {
        return 0;
    }
}