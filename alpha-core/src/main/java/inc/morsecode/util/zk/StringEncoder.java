package inc.morsecode.util.zk;

import java.io.*;

public class StringEncoder implements StorageCodec<String> {

    @Override
    public byte[] write(Object value) throws IOException {
        if (value == null) { return "null".getBytes(); }
        return value.toString().getBytes();
    }

    @Override
    public String read(byte[] bytes) throws IOException {
        try {
            return new String(bytes);
        } catch (Exception x) {
            return null;
        }
    }

}
