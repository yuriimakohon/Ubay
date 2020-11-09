package world.ucode.API.authorization;

import org.json.simple.JSONObject;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.model.db.entetis.Users;
import world.ucode.utils.ParseJson;
import world.ucode.utils.ReadRequestToString;
import world.ucode.utils.RegExp;
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
        String json = ReadRequestToString.ReadToString(req);
        JSONObject joReq = ParseJson.jsonToJsonObject(json);

        String password = joReq.get("password").toString();
        String login = joReq.get("login").toString();

        if (!RegExp.checkRegExp("^[A-Za-z0-9]{3,21}$", login) || !RegExp.checkRegExp("^[a-z0-9]{128}$", password)) {
            resp.setStatus(406);
            resp.getWriter().write("fuck you, wrong parse");
            return;
        }
        System.out.println(password);
        System.out.println(password.length());

        Users user = DAOUser.readbyLogin(login);

        if (user == null) {
            resp.setStatus(265);
            resp.getWriter().write("user does not exists");
        } else {
            if (!user.userValidPassword(login, password)) {
                resp.setStatus(264); // error password
                resp.getWriter().write("fuck you");
            } else {
                resp.setStatus(200);
                JSONObject jo = new JSONObject();
                jo.put("token", new Token().getToken(login));
                jo.put("id", user.getId());
                resp.getWriter().write(jo.toJSONString());
            }
        }
    }
}
