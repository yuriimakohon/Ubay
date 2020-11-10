package world.ucode.utils;

import org.json.simple.JSONObject;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.model.db.entetis.Users;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

public class RequestObject {
    private String idString;
    private String token;
    private String login;
    private int id;

    public JSONObject jo = new JSONObject();

    public boolean ok = true;
    public Users user;

    private String resp;
    private int status;


    public void checkJson(HttpServletRequest req) throws IOException {
        if (!ok) {
            return;
        }
        jo = ParseJson.jsonToJsonObject(ReadRequestToString.ReadToString(req));

        if (jo == null) {
            ok = false;
            status = 406;
            resp = "hm your json is bad";
        }
    }

    public RequestObject(){}

    public void checkCookie(Cookie[] cookies, DAOusers DAOUser) {
        if (!ok) {
            return;
        }
        HashMap<String, String> mapCookie = ParseCookie.parseToMap(cookies);
        String idString = mapCookie.get("id");
        String token = mapCookie.get("token");

        if (token == null || idString == null) {
            status = 200;
            jo.put("role", 0);
            resp = jo.toJSONString();
            ok = false;
            return;
        }
        int id = Integer.parseInt(idString);

        user = DAOUser.readByTokenAndId(token, id);
        if (user == null) {
            status = 200;
            jo.put("role", 0);
            resp = jo.toJSONString();
            ok = false;
            return;
        }
        status = 200;
    }

    public String getResp() {
        return resp;
    }

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }
}
