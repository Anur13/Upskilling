package com.andrew.decoratorsIO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BufferedOutputStream extends OutputStream {
    private final int DEFAULT_BUFFER_SIZE = 10;
    private InputStream input;
    private byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];


    @Override
    public void write(int b) throws IOException {

    }

    @Override
    public void write(byte[] b) throws IOException {
        super.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        super.write(b, off, len);
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