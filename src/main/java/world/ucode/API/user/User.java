package world.ucode.API.user;

import org.json.simple.JSONObject;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.model.db.entetis.Users;
import world.ucode.utils.Enums;
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
        int id = Utils.getId(req);
        resp.setContentType("application/json;charset=utf-8");
        JSONObject jo = new JSONObject();

        if (id == -1) {
            resp.setStatus(200);
            jo.put("role", "0");
        } else {
            Users user = daoUser.read(id);
            if (user == null) {
                resp.setStatus(404);
                jo.put("role", "0");
            } else {
                jo.put("login", user.getLogin());
                jo.put("role", user.getUserRole());
                jo.put("balance", user.getBalance());
                jo.put("id", user.getId());
                jo.put("avatar", user.getUserphoto());
                resp.setStatus(200);
            }
        }
        resp.getWriter().write(jo.toJSONString());
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
        } else if (param.equals("change_photo")) {
            UserUtils.changePhoto(req, resp, daoUser);
        } else {
            resp.setStatus(406);
            resp.getWriter().write("param not found");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
