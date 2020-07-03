package client.java.utils;

import java.io.Closeable;
import java.io.IOException;

public class SxUtils {
    public static void close(Closeable... targets) {
        for (Closeable target : targets) {
            try {
                if (null != target)
                    target.close();
            } catch (IOException e) {
            }
        }
    }
}


