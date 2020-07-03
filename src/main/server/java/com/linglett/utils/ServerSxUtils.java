package server.java.com.linglett.utils;

import java.io.Closeable;
import java.io.IOException;

public class ServerSxUtils {
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
