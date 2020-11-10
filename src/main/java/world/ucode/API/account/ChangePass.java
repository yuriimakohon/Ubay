package world.ucode.API.account;

import org.json.simple.JSONObject;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.model.db.entetis.Users;
import world.ucode.utils.ParseJson;
import world.ucode.utils.ReadRequestToString;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/account/change_pass")
public class ChangePass extends HttpServlet {
    DAOusers DAOUser;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        DAOUser = new DAOusers();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = ReadRequestToString.ReadToString(req);
        JSONObject joReq = ParseJson.jsonToJsonObject(json);

        if (joReq == null) {
            resp.setStatus(406);
            resp.getWriter().write("hm your json is not valid");
            return;
        }

        int id = -1;
        String token = "";
        String password = joReq.get("password").toString();

        Cookie[] cooks = req.getCookies();
        for (Cookie cook : cooks) {
            if (cook.getName().equals("id")) {
                id = Integer.parseInt(cook.getValue());
            } else if (cook.getName().equals("token")) {
                token = cook.getValue();
            }
        }
        Users user = DAOUser.read(id);

        System.out.println(id);
        System.out.println(user.getToken());
        System.out.println(token);

        if (user.getToken().equals(token)) {
            user.setPassword(password);
            DAOUser.update(user);
            resp.setStatus(200);
        } else {
            resp.setStatus(401);
            resp.getWriter().write("are you hacker ?");
        }
    }
}
