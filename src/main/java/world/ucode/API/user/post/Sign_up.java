package world.ucode.API.user.post;

import org.json.simple.JSONObject;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.model.db.entetis.Users;
import world.ucode.utils.ParseJson;
import world.ucode.utils.ReadRequestToString;
import world.ucode.utils.Utils;
import world.ucode.utils.token.Token;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/api/user/sign_up")
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


        if (Utils.checkValidLogin(login) || Utils.checkValidPassword(password) || Utils.checkValidRole(role)) {
            resp.setStatus(406);
            resp.getWriter().write("validation fail");
            return;
        }

        if (DAOUser.readByLogin(login) == null) {
            Users user = new Users("", login, password, Integer.parseInt(role));
            user.setUserPhoto("/resources/user.svg");
            DAOUser.create(user);
            Token.setTokens(user, DAOUser, resp);
            JSONObject jo = new JSONObject();

            jo.put("tokenTime", Token.getTimeOfToken());
            jo.put("time", new Date().getTime());
            jo.put("balance", user.getBalance());
            jo.put("role", user.getUserRole());
            jo.put("id", user.getId());
            jo.put("avatar", user.getUserphoto());

            resp.getWriter().write(jo.toJSONString());
            resp.setStatus(200);
        }
        else {
            resp.setStatus(409); // user already exists
            resp.getWriter().write("login is exists");
        }
    }
}