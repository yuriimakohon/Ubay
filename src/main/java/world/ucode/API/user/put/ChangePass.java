package world.ucode.API.user.put;

import world.ucode.model.db.dao.DAOusers;
import world.ucode.utils.RequestObject;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        RequestObject ro = new RequestObject();

        ro.checkJson(req);
        ro.checkCookie(req.getCookies(), DAOUser);

        if (!ro.ok) {
            resp.setStatus(ro.getStatus());
            resp.getWriter().write(ro.getResp());
        } else {
            ro.user.setPassword(ro.jo.get("password").toString());
            DAOUser.update(ro.user);
            resp.setStatus(ro.getStatus());
        }
    }
}
