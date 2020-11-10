package world.ucode.API.account;

import world.ucode.model.db.dao.DAOusers;
import world.ucode.utils.RequestObject;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        RequestObject ro = new RequestObject();
        ro.checkCookie(req.getCookies(), DAOUser);

        if (!ro.ok) {
            resp.setStatus(ro.getStatus());
            resp.getWriter().write(ro.getResp());
        } else {
            ro.jo.put("login", ro.user.getLogin());
            ro.jo.put("role", ro.user.getUserRole());
            resp.getWriter().write(ro.jo.toJSONString());
            resp.setStatus(ro.getStatus());
        }
    }
}
