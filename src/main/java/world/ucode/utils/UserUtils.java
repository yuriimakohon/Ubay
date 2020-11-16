package world.ucode.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.model.db.entetis.Users;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;

public class UserUtils {
    public static void badJson(HttpServletResponse resp) throws IOException {
        resp.setStatus(406);
        resp.getWriter().write("bad json");
    }

    public static void changePass(HttpServletRequest req, HttpServletResponse resp, DAOusers daoUser) throws IOException {
        RequestObject ro = new RequestObject();

        ro.checkJson(req);
        ro.checkCookie(req.getCookies(), daoUser);

        if (!ro.ok) {
            resp.setStatus(ro.getStatus());
            resp.getWriter().write(ro.getResp());
        } else {
            String pass = ro.jo.get("password").toString();

            if (pass == null) {
                badJson(resp);
            } else {
                ro.user.setPassword(pass);
                daoUser.update(ro.user);
                resp.setStatus(ro.getStatus());
            }
        }
    }

    public static void changeLogin(HttpServletRequest req, HttpServletResponse resp, DAOusers daoUser) throws IOException {
        RequestObject ro = new RequestObject();

        ro.checkJson(req);
        ro.checkCookie(req.getCookies(), daoUser);

        if (!ro.ok) {
            resp.setStatus(ro.getStatus());
            resp.getWriter().write(ro.getResp());
        } else {
            String login = ro.jo.get("login").toString();

            if (login == null)  {
                badJson(resp);
            } else {
                Users user = daoUser.readByLogin(login);

                if (user != null) {
                    resp.setStatus(406);
                    resp.getWriter().write("this login is booked");
                } else {
                    ro.user.setLogin(ro.jo.get("login").toString());
                    daoUser.update(ro.user);
                    resp.setStatus(ro.getStatus());
                }
            }
        }
    }

    public static void changeBalance(HttpServletRequest req, HttpServletResponse resp, DAOusers daoUser) throws IOException {
        RequestObject ro = new RequestObject();

        ro.checkJson(req);
        ro.checkCookie(req.getCookies(), daoUser);

        if (ro.ok) {
            long balance;

            try {
                balance = Long.parseLong(ro.jo.get("balance").toString());
            } catch (NumberFormatException e) {
                resp.setStatus(409);
                resp.getWriter().write("validation fail");
                return;
            }

            if (ro.user.getBalance() + balance < 0) {
                resp.setStatus(409);
                resp.getWriter().write("small many");
            } else {
                ro.user.setBalance(ro.user.getBalance() + balance);
                daoUser.update(ro.user);
                resp.setStatus(ro.getStatus());
            }
        } else {
            resp.setStatus(409);
            resp.getWriter().write("validation fail");
        }
    }

    public static void logOut(HttpServletRequest req, HttpServletResponse resp, DAOusers daoUser) {
        Cookie[] cookies = req.getCookies();

        HashMap<String, String> cm = ParseCookie.parseToMap(cookies);
        String idS = cm.get("id");

        if (idS != null) {
            Users user = daoUser.read(Integer.parseInt(idS));
            if (user != null) {
                user.setRef_token("");
                user.setToken("");
                daoUser.update(user);
            }
        }
        for (Cookie cookie : cookies) {
            cookie.setValue("");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
        }
    }

    public static void changePhoto(HttpServletRequest req, HttpServletResponse resp, DAOusers daoUser) throws IOException {
        RequestObject ro = new RequestObject();

        ro.checkJson(req);
        ro.checkCookie(req.getCookies(), daoUser);

        if (!ro.ok) {
            resp.setStatus(ro.getStatus());
            resp.getWriter().write(ro.getResp());
        } else {
            String path = "src/main/webapp/resources/";
            File user_dir = new File(path + ro.user.getId());
            File user_avatar = new File(path + ro.user.getId() + "/" + "0.png");

            if (!user_dir.exists()) {
                user_dir.mkdir();
            }
            if (user_avatar.exists()) {
                user_avatar.delete();
                user_avatar.createNewFile();
            }

            JSONArray ja = (JSONArray) ro.jo.get("photo");
            String photoString = ja.get(0).toString();
            if (photoString == null) {
                resp.setStatus(406);
                resp.getWriter().write("bad json");
                return;
            }

            byte[] data = Base64.getDecoder().decode(photoString.split(",")[1]);
            FileOutputStream fos = new FileOutputStream(user_avatar);
            fos.write(data);

            resp.setStatus(200);
            JSONObject respJSON = new JSONObject();
            respJSON.put("avatar", "/resources/"+ro.user.getId()+"/0.png");
            resp.getWriter().write(respJSON.toJSONString());
            ro.user.setUserPhoto("/resources/"+ro.user.getId()+"/0.png");
            daoUser.update(ro.user);
        }
    }
}
