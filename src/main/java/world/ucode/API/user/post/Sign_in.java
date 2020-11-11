package world.ucode.API.user.post;

import org.json.simple.JSONObject;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.model.db.entetis.Users;
import world.ucode.utils.ParseJson;
import world.ucode.utils.ReadRequestToString;
import world.ucode.utils.RegExp;
import world.ucode.utils.Utils;
import world.ucode.utils.token.Token;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/sign_in")
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

        if (!Utils.checkValidLogin(login) || !Utils.checkValidPassword(password)) {
            resp.setStatus(406);
            resp.getWriter().write("fuck you hacker, wrong parse");
            return;
        }
        Users user = DAOUser.readByLogin(login);

        if (user == null) {
            resp.setStatus(409);
            resp.getWriter().write("what is wrong ?");
        } else {
            if (!user.userValidPassword(login, password)) {
                resp.setStatus(409); // error password
                resp.getWriter().write("fuck you");
            } else {
                resp.setStatus(200);
                Token.setTokens(user, DAOUser, resp);
                resp.getWriter().write(String.valueOf(Token.getTimeOfToken()));
            }
        }
    }
}
