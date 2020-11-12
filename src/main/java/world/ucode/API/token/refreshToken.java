package world.ucode.API.token;


import world.ucode.model.db.dao.DAOusers;
import world.ucode.model.db.entetis.Users;
import world.ucode.utils.ParseCookie;
import world.ucode.utils.token.Token;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/refresh_token")
public class refreshToken extends HttpServlet {
    DAOusers daoUser;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        daoUser = new DAOusers();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, String> cm = ParseCookie.parseToMap(req.getCookies());
        String ref_token = cm.get("ref_token");
        String idS = cm.get("id");

        if (ref_token == null || idS == null) {
            resp.setStatus(406);
            return;
        }

        Users user = daoUser.read(Integer.parseInt(idS));

        if (user == null || !user.getRef_token().equals(ref_token)) {
            resp.setStatus(406);
            return;
        }
        Token.createSetTokens(user, resp, daoUser);
    }
}
