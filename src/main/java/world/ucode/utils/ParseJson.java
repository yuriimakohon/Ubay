package world.ucode.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ParseJson {
    public static JSONObject jsonToJsonObject(String json) {
        JSONParser parser = new JSONParser();
        JSONObject jo;

        try {
            jo = (JSONObject) parser.parse(String.valueOf(json));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            jo = null;
        }

        return jo;
    }
}
