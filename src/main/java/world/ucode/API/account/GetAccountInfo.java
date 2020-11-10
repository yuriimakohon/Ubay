package world.ucode.API.account;

import org.json.simple.JSONObject;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.model.db.entetis.Users;
import world.ucode.utils.ParseCookie;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


@WebServlet("/get_user_info")
public class GetAccountInfo extends HttpServlet {
    DAOusers DAOUser;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        DAOUser = new DAOusers();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, String> mapCookie = ParseCookie.parseToMap(req.getCookies());
        String idString = mapCookie.get("id");
        String token = mapCookie.get("token");
        JSONObject jo = new JSONObject();

        if (token == null || idString == null) {
            resp.setStatus(200);
            jo.put("role", 0);
            resp.getWriter().write(jo.toJSONString());
            return;
        }

        int id = Integer.parseInt(idString);
        System.out.println("id: " + id);
        System.out.println("token: " + token);
        Users user = DAOUser.read(id);

        System.out.println("\nid: " + user.getId());
        System.out.println("token: " + user.getToken());

        if (user.getToken().equals(token)) {
            jo.put("login", user.getLogin());
            jo.put("role", user.getUserRole());
            resp.getWriter().write(jo.toJSONString());
            resp.setStatus(200);
        } else {
            resp.setStatus(401);
            resp.getWriter().write("are you hacker ?");
        }
    }
}
