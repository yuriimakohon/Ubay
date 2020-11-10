package world.ucode.API.account;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.model.db.entetis.Users;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/account/get_info")
public class GetAccountInfo extends HttpServlet {
    DAOusers DAOUser;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        DAOUser = new DAOusers();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("account get info");
        int id = -1;
        String token = "";

        Cookie[] cooks = req.getCookies();
        for (Cookie cook : cooks) {
            if (cook.getName().equals("id")) {
                id = Integer.parseInt(cook.getValue());
            } else if (cook.getName().equals("token")) {
                token = cook.getValue();
            }
        }
        Users user = DAOUser.read(id);
        if (user.getToken().equals(token)) {
            JSONObject jo = new JSONObject();
            jo.put("login", user.getLogin());
            resp.getWriter().write(jo.toJSONString());
            resp.setStatus(200);
        } else {
            resp.setStatus(401);
            resp.getWriter().write("are you hacker ?");
        }
    }
}
