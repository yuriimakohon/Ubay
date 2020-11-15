package world.ucode.API.user;

import world.ucode.model.db.dao.DAOusers;
import world.ucode.utils.RequestObject;
import world.ucode.utils.UserUtils;
import world.ucode.utils.Utils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/user/*")
public class User extends HttpServlet {
    DAOusers daoUser;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        daoUser = new DAOusers();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestObject ro = new RequestObject();
        ro.checkCookie(req.getCookies(), daoUser);
        int id = Utils.getId(req);

        if (!ro.ok) {
            resp.setStatus(ro.getStatus());
            resp.getWriter().write(ro.getResp());
        } else if (id == -1) {
            ro.jo.put("login", ro.user.getLogin());
            ro.jo.put("role", ro.user.getUserRole());
            ro.jo.put("balance", ro.user.getBalance());
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(ro.jo.toJSONString());
            resp.setStatus(ro.getStatus());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("tab");

        if (param == null) {
            resp.setStatus(406);
            resp.getWriter().write("param not found");
        } else if (param.equals("change_balance")) {
            UserUtils.changeBalance(req, resp, daoUser);
        } else if (param.equals("change_login")) {
            UserUtils.changeLogin(req, resp, daoUser);
        } else if (param.equals("change_pass")) {
            UserUtils.changePass(req, resp, daoUser);
        } else if (param.equals("log_out")) {
            UserUtils.logOut(req, resp, daoUser);
        } else {
            resp.setStatus(406);
            resp.getWriter().write("param not found");
        }
    }
}
