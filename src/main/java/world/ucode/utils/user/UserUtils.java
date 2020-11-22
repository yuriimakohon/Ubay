package world.ucode.utils.user;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.model.db.entetis.Users;
import world.ucode.utils.Interaces.RestUtils;
import world.ucode.utils.ParseCookie;
import world.ucode.utils.RequestObject;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;

public class UserUtils implements RestUtils {
    private final DAOusers daoUser;

    public UserUtils() {
        daoUser = new DAOusers();
    }

    @Override
    public JSONObject get(int id , HttpServletResponse resp) {
        JSONObject jo = new JSONObject();

        Users user = daoUser.read(id);
        if (user == null) {
            jo.put("role", "0");
            jo.put("ok", false);
        } else {
            jo.put("status", "200");
            jo.put("login", user.getLogin());
            jo.put("role", user.getUserRole());
            jo.put("balance", user.getBalance());
            jo.put("id", user.getId());
            jo.put("avatar", user.getUserphoto());
            jo.put("ok", true);
        }
        resp.setStatus(200);
        return jo;
    }

    @Override
    public JSONObject create(HttpServletRequest req, HttpServletResponse resp) {
        // TO DO
        return null;
    }

    @Override
    public JSONObject put(HttpServletRequest req, HttpServletResponse resp) {
        JSONObject jo = new JSONObject();

        String param = req.getParameter("tab");

        switch (param) {
            case "change_balance":
                this.changeBalance(req, resp);
                break;
            case "change_login":
                this.changeLogin(req, resp);
                break;
            case "change_pass":
                this.changePass(req, resp);
                break;
            case "log_out":
                this.logOut(req, resp);
                break;
            case "change_photo":
                this.changePhoto(req, resp);
                String avatar = req.getAttribute("avatar").toString();
                if (avatar != null) {
                    jo.put("avatar", req.getAttribute("avatar"));
                }
                break;
            default:
                resp.setStatus(400);
        }
        if (resp.getStatus() >= 400) {
            jo.put("ok", false);
        } else {
            jo.put("ok", true);
        }
        return jo;
    }

    @Override
    public JSONObject delete(int id, HttpServletResponse resp) {
        // TO DO
        return null;
    }

    @Override
    public JSONArray get_all() {
        // TO DO
        return null;
    }

    public void changePass(HttpServletRequest req, HttpServletResponse resp) {
        RequestObject ro = new RequestObject();

        ro.checkCookie(req.getCookies(), daoUser);
        ro.checkJson(req);

        if (!ro.ok) {
            resp.setStatus(409);
        } else {
            String pass = ro.jo.get("password").toString();

            if (pass == null) {
                resp.setStatus(409);
            } else {
                ro.user.setPassword(pass);
                daoUser.update(ro.user);
                resp.setStatus(ro.getStatus());
            }
        }
    }

    public void changeLogin(HttpServletRequest req, HttpServletResponse resp) {
        RequestObject ro = new RequestObject();

        ro.checkCookie(req.getCookies(), daoUser);
        ro.checkJson(req);

        if (!ro.ok) {
            resp.setStatus(409);
        } else {
            String login = ro.jo.get("login").toString();

            if (login == null)  {
                resp.setStatus(409);
            } else {
                Users user = daoUser.readByLogin(login);

                if (user != null) {
                    resp.setStatus(406);
                } else {
                    ro.user.setLogin(ro.jo.get("login").toString());
                    daoUser.update(ro.user);
                    resp.setStatus(ro.getStatus());
                }
            }
        }
    }

    public void changeBalance(HttpServletRequest req, HttpServletResponse resp) {
        RequestObject ro = new RequestObject();

        ro.checkJson(req);
        ro.checkCookie(req.getCookies(), daoUser);

        if (ro.ok) {
            double balance;

            try {
                balance = Double.parseDouble(ro.jo.get("balance").toString());
            } catch (NumberFormatException e) {
                resp.setStatus(406);
                return;
            }

            if (ro.user.getBalance() + balance < 0) {
                resp.setStatus(409);
            } else {
                ro.user.setBalance(ro.user.getBalance() + balance);
                daoUser.update(ro.user);
                resp.setStatus(ro.getStatus());
            }
        } else {
            resp.setStatus(409);
        }
    }

    public void logOut(HttpServletRequest req, HttpServletResponse resp) {
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

    public void changePhoto(HttpServletRequest req, HttpServletResponse resp) {
        RequestObject ro = new RequestObject();

        ro.checkCookie(req.getCookies(), daoUser);
        ro.checkJson(req);

        if (!ro.ok) {
            resp.setStatus(409);
        } else {
            String path = "src/main/webapp/resources/";
            File user_dir = new File(path + ro.user.getId());
            File user_avatar = new File(path + ro.user.getId() + "/" + "0.png");

            try {
                if (!user_dir.exists()) {
                    user_dir.mkdir();
                }
                if (user_avatar.exists()) {
                    user_avatar.delete();
                    user_avatar.createNewFile();
                }
            } catch (IOException e) {
                resp.setStatus(409);
            }

            JSONArray ja = (JSONArray) ro.jo.get("photo");
            String photoString = ja.get(0).toString();
            if (photoString == null) {
                resp.setStatus(406);
                return;
            }

            byte[] data = Base64.getDecoder().decode(photoString.split(",")[1]);
            try {
                FileOutputStream fos = new FileOutputStream(user_avatar);
                fos.write(data);
            } catch (IOException e) {
                resp.setStatus(406);
                return;
            }
            resp.setStatus(200);
            req.setAttribute("avatar", "/resources/"+ro.user.getId()+"/0.png");
            ro.user.setUserPhoto("/resources/"+ro.user.getId()+"/0.png");
            daoUser.update(ro.user);
        }
    }

    public static JSONObject getJSONObject(int id) { ;
        DAOusers daoUsers = new DAOusers();
        Users user = daoUsers.read(id);

        if (user == null) {
            return null;
        }

        JSONObject jo = new JSONObject();
        jo.put("login", user.getLogin());
        jo.put("role", user.getUserRole());
        jo.put("avatar", user.getUserphoto());
        jo.put("id", user.getId());
        jo.put("balance", user.getBalance());

        return jo;
    }
}
