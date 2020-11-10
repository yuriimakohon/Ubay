package world.ucode.API.catalog;


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

@WebServlet("/catalog/get_auctions")
public class GetAuctions extends HttpServlet {
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
        if (token == null || idString == null) {
            resp.setStatus(401);
            resp.getWriter().write("are you hacker ? there is no token or id");
            return;
        }
        int id = Integer.parseInt(idString);

        Users user = DAOUser.read(id);

        if (token.equals(user.getToken())) {
            JSONObject jo = new JSONObject();
            resp.setStatus(200);
            resp.getWriter().write(jo.toJSONString());
        } else {
            resp.setStatus(401);
            resp.getWriter().write("are you hacker ? there is no token or id");
        }
    }
}
