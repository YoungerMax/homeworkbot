package me.youngermax.homeworkbot.util;

import java.io.IOException;
import java.io.InputStream;

public class InputStreamUtils {
    public static String read(InputStream stream) throws IOException {
        StringBuilder str = new StringBuilder();
        byte[] buffer = new byte[1024];

        for (int i; (i = stream.read(buffer)) > -1;) {
            str.append(new String(buffer), 0, i);
        }

        return str.toString();
    }
}
