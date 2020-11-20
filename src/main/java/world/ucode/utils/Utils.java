package world.ucode.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import world.ucode.model.db.dao.DAOlot;
import world.ucode.model.db.entetis.Lot;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

        if (pathInfo == null || pathInfo.equals("") || pathInfo.equals("/")) {
            return 0;
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

    public static JSONArray lotsToJsonArray(List<Lot> lots) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JSONArray ja = new JSONArray();
        JSONParser jp = new JSONParser();
        JSONObject jo;
        String json;

        for (Lot lot : lots) {
            json = mapper.writeValueAsString(lot);
            try {
                jo = (JSONObject) jp.parse(json);
                ja.add(jo);
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        }
        return ja;
    }
}
