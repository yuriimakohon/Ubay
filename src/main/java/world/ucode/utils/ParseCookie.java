package world.ucode.utils;

import javax.servlet.http.Cookie;
import java.util.HashMap;

public class ParseCookie {
    public static HashMap<String, String> parseToMap(Cookie[] cookies) {
        HashMap<String, String> mc = new HashMap<>();
        for (Cookie cook : cookies) {
            mc.put(cook.getName(), cook.getValue());
        }
        return mc;
    }
    public static String getElementCookie(Cookie[] cookies, String element) throws Exception {
        for (Cookie cook : cookies) {
            if (cook.getName().equals(element)) {
                return cook.getValue();
            }
        }
        throw new Exception("cookie not found");
    }
}
