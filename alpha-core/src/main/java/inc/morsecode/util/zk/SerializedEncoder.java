package inc.morsecode.util.zk;

import java.io.*;

public class SerializedEncoder implements StorageCodec<Object> {

    @Override
    public byte[] write(Object value) throws IOException {
        ByteArrayOutputStream bytes= new ByteArrayOutputStream();
        ObjectOutputStream writer= new ObjectOutputStream(bytes);
        try {
            if (value instanceof String) {
                return ((String) value).getBytes();
            } else if (value instanceof Integer ||
                    value instanceof Byte ||
                    value instanceof Float ||
                    value instanceof Double ||
                    value instanceof Long
                    ) {
                // simple string representation
                return ("" + value).getBytes();
            } else if (value instanceof Boolean) {
                bytes.write(((Boolean) value) ? 1 : 0);
            } else {
                // java object serialization
                writer.writeObject(value);
            }
        }finally {
            writer.close();
            bytes.close();
        }
        return bytes.toByteArray();

    }

    @Override
    public Object read(byte[] bytes) throws IOException {
        ObjectInputStream reader= new ObjectInputStream(new ByteArrayInputStream(bytes));
        try {
            Object o= reader.readObject();
            if (o instanceof String) {
                return inferType((String)o);
            }
            return o;
        } catch (ClassNotFoundException cnfx) {
            cnfx.printStackTrace();
            return null;
        }
    }

    public static boolean looksNumeric(String string) {
        return string.matches("^[-0-9][0-9]*[\\.0-9][0-9]*$"); // ||
    }

    private Object inferType(String value) {

        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException notDouble) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException notInt) {
                try {
                    return Long.parseLong(value);
                } catch (NumberFormatException notLong) {
                }
            }
        }

        if ("false".equalsIgnoreCase(value)) { return false; }
        if ("true".equalsIgnoreCase(value)) { return true; }

        return value;   // leave it as a string
    }
}
