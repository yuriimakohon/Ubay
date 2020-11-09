package world.ucode.API.authorization;

import org.json.simple.JSONObject;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.model.db.entetis.Users;
import world.ucode.utils.ParseJson;
import world.ucode.utils.ReadRequestToString;
import world.ucode.utils.token.Token;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign_up")
public class Sign_up extends HttpServlet {
    private DAOusers DAOUser;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        DAOUser = new DAOusers();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = ReadRequestToString.ReadToString(req);
        JSONObject joReq = ParseJson.jsonToJsonObject(json);

        String password = joReq.get("password").toString();
        String login = joReq.get("login").toString();
        String role = joReq.get("role").toString();

        String token = new Token().getToken(login);

        if (DAOUser.readbyLogin(login) == null) {
            System.out.println("sign up ok");
            Users user = new Users(token, login, password, role);
            DAOUser.create(user);
            resp.setStatus(200);
            JSONObject jo = new JSONObject();
            jo.put("token", new Token().getToken(login));
            resp.getWriter().write(jo.toJSONString());
        }
        else {
            System.out.println("sign up error");
            resp.setStatus(266); // user already exists
            resp.getWriter().write("fuck you");
        }
    }
}