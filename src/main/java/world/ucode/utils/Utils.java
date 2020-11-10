package world.ucode.utils;

import java.util.HashMap;
import java.util.Map;


public class Utils {
    public static <T1, T2> void printHashMap(HashMap<T1, T2> hm) {
        for (Map.Entry<T1, T2> entry : hm.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    public static boolean checkValidLogin(String login) {
        return RegExp.checkRegExp("^[A-Za-z0-9]{3,21}$", login);
    }
    public static boolean checkValidPassword(String password) {
        return RegExp.checkRegExp("^[a-z0-9]{128}$", password);
    }
    public static boolean checkValidRole(String role) {
        return RegExp.checkRegExp("^[0-9]{1,5}$", role);
    }
}
