package utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteStreamConverter {

    private ByteStreamConverter() {
    }

    private static class SingletonHelper {
        private static final ByteStreamConverter INSTANCE = new ByteStreamConverter();
    }

    public static ByteStreamConverter getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public byte[] convertToByteArray(InputStream fileStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = fileStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        byte[] byteArray = buffer.toByteArray();
        buffer.close();
        return byteArray;
    }
}