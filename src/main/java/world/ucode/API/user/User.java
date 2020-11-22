package world.ucode.API.user;

import org.json.simple.JSONObject;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.utils.user.UserUtils;
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
    UserUtils utils;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        daoUser = new DAOusers();
        utils = new UserUtils();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Utils.getId(req);
        resp.setContentType("application/json;charset=utf-8");
        JSONObject jo = new JSONObject();

        if (id == -1 || id == 0) {
            jo.put("ok", false);
            jo.put("role", "0");
        } else if (id > 0) {
            jo = utils.get(id, resp);
        }
        resp.getWriter().write(jo.toJSONString());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String param = req.getParameter("tab");

        if (param == null) {
            resp.setStatus(406);
            resp.getWriter().write("param not found");
        }
        JSONObject jo = utils.put(req, resp);
        resp.getWriter().write(jo.toJSONString());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TO DO
    }
}
