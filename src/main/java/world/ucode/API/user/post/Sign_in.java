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
import java.util.Date;

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
            resp.getWriter().write("validation fail");
            return;
        }
        Users user = DAOUser.readByLogin(login);

        if (user == null) {
            resp.setStatus(409);
            resp.getWriter().write("user not found");
        } else {
            if (!user.userValidPassword(login, password)) {
                resp.setStatus(409); // error password
                resp.getWriter().write("wrong password");
            } else {
                JSONObject jo = new JSONObject();
                Token.setTokens(user, DAOUser, resp);

                jo.put("tokenTime", Token.getTimeOfToken());
                jo.put("balance", user.getBalance());
                jo.put("time", new Date().getTime());
                jo.put("role", user.getUserRole());

                resp.getWriter().write(jo.toJSONString());
                resp.setStatus(200);
            }
        }
    }
}
