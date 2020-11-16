package world.ucode.API.pages;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import world.ucode.utils.Utils;
import world.ucode.utils.user.GetUserAPI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/*")
public class User extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Utils.getId(req);

        if (id == 0) {
            resp.setStatus(404);
            System.out.println("id == 0");
        } else {
            try {
                GetUserAPI gua = new GetUserAPI(id);
                if (gua.status >= 400) {
                    resp.setStatus(404);
                    System.out.println("error: get gua");
                } else {
                    JSONParser jp = new JSONParser();
                    JSONObject jo = null;
                    jo = (JSONObject) jp.parse(gua.json);
                    req.setAttribute("user", jo.toJSONString());
                }
            }
            catch (ParseException e) {
                resp.setStatus(404);
                System.out.println("error: " + e.getMessage());
            }
        }
        if (resp.getStatus() == 404) {
            String errorPath = req.getServletPath();
            if (req.getPathInfo() != null) {
                errorPath += req.getPathInfo();
            }
            req.setAttribute("path", errorPath);
            req.getRequestDispatcher("/jsp/404.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/jsp/user.jsp").forward(req, resp);
        }
    }
}
