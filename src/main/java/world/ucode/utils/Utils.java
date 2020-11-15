package world.ucode.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


public class Utils {
    public static <T1, T2> void printHashMap(HashMap<T1, T2> hm) {
        for (Map.Entry<T1, T2> entry : hm.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    public static boolean checkValidLogin(String login) {
        return login == null || !RegExp.checkRegExp("^[A-Za-z0-9]{3,21}$", login);
    }
    public static boolean checkValidPassword(String password) {
        return password == null || !RegExp.checkRegExp("^[a-z0-9]{128}$", password);
    }

    public static int getId(HttpServletRequest req) {
        String pathInfo = req.getPathInfo();

        if (pathInfo == null) {
            return -1;
        }
        String[] params = req.getPathInfo().split("/");
        String param = params[params.length-1];

        int lotId;
        try {
            lotId = Integer.parseInt(param);
        } catch (NumberFormatException e) {
            return -1;
        }
        return lotId;
    }

    public static boolean checkValidRole(String role) {
        return role == null || !RegExp.checkRegExp("^[0-9]{1,5}$", role);
    }
}
