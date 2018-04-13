package inc.morsecode.zk;

import java.io.IOException;

public interface StorageCodec<T> {
    byte[] write(Object value) throws IOException;

    T read(byte[] bytes) throws IOException;
}
