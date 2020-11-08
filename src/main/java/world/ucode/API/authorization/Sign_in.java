package world.ucode.API.authorization;

import org.json.simple.JSONObject;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.model.db.entetis.Users;
import world.ucode.utils.token.Token;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign_in")
public class Sign_in extends HttpServlet {
    private DAOusers DAOUser;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        DAOUser = new DAOusers();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users user = DAOUser.readbyLogin(req.getParameter("login"));
        String login = req.getParameter("login");

        if (user == null) {
            resp.setStatus(265);
            resp.getWriter().write("user is exists");
        } else {
            if (user.userValidPassword(login, req.getParameter("password"))) {
                resp.setStatus(264);
            } else {
                resp.setStatus(200);

                JSONObject jo = new JSONObject();
                jo.put("token", new Token().getToken(login));
                resp.getWriter().write(jo.toJSONString());
            }
        }
//        resp.setStatus(200);
//        System.out.println(req.getParameter("login"));
//        System.out.println(req.getParameter("password"));
//        System.out.println(new Token().getToken(req.getParameter("login")));
    }
}
