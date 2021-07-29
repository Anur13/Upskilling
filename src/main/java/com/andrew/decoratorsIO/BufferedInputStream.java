package com.andrew.decoratorsIO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

class BufferedInputStream extends InputStream {
    static byte[] buffer;
    private int bufferCount;
    private InputStream inputStream;

    public BufferedInputStream(InputStream inputStream) throws IOException {
        this(inputStream, 4);
    }
    public BufferedInputStream(InputStream inputStream, int size) throws IOException {
        this.inputStream = inputStream;
        buffer = new byte[size];
        copyBytesIntoBuffer();
    }



    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        System.arraycopy(buffer, 0, b, off, len);
        return len;
    }

    @Override
    public void close() throws IOException {
        Arrays.fill(buffer, (byte) 0);
        inputStream.close();
    }

    @Override
    public int read() throws IOException {
        checkNeedToReFill();
        if (buffer[bufferCount] != -1) {
            return buffer[bufferCount++];
        }
        return -1;
    }

    private void checkNeedToReFill() throws IOException {
        if (bufferCount == buffer.length) {
            copyBytesIntoBuffer();
            bufferCount = 0;
        }
    }

    private void copyBytesIntoBuffer() throws IOException {
        int inputByte;
        for (int i = 0; i < buffer.length; i++) {
            if ((inputByte = inputStream.read()) != -1) {
                buffer[i] = (byte) inputByte;
            } else {
                buffer[i] = -1;
                return;
            }
        }
    }
}