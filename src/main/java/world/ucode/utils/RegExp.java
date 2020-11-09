package world.ucode.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExp {
    public static boolean checkRegExp(String strPattern, String strMatcher) {
        Pattern pattern = Pattern.compile(strPattern);
        Matcher matcher = pattern.matcher(strMatcher);
        return matcher.find();
    }
}
