package world.ucode.API.user.put;

import world.ucode.model.db.dao.DAOusers;
import world.ucode.model.db.entetis.Users;
import world.ucode.utils.ParseCookie;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/user/log_out")
public class LogOut extends HttpServlet {
    DAOusers daoUser;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        daoUser = new DAOusers();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
}
