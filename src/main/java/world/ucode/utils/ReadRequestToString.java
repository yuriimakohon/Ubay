package world.ucode.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class ReadRequestToString {
    public static String ReadToString(HttpServletRequest req) throws IOException {
        BufferedReader bf = req.getReader();
        String buff = null;
        StringBuilder json = new StringBuilder();

        while ((buff = bf.readLine()) != null) {
            json.append(buff);
        }
        return new String(json);
    }
}
