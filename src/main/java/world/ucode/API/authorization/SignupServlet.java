package world.ucode.API.authorization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import world.ucode.objects.User;
import world.ucode.objects.auction;
import world.ucode.utils.ParseJson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/auth")
public class SignupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/jsp/auth.jsp");
        requestDispatcher.forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(201);
        resp.setContentType("text/plain");
        User user = new User();

        BufferedReader bf = req.getReader();
        String buff = null;
        StringBuilder json = new StringBuilder();

        while ((buff = bf.readLine()) != null) {
            json.append(buff);
        }

        JSONObject jo = ParseJson.jsonToJsonObject(new String(json));
        if (jo == null) {
            resp.setStatus(499);
            resp.getWriter().write("error");
        }
        assert jo != null;
        user.setLogin(jo.get("login").toString());
        user.setPassword(jo.get("password").toString());

        System.out.println(json);
        if (req.getHeader("request").equals("sign_up")) {
            resp.getWriter().write("sign_up ok, id: '', token: ''");
            user.setRole(jo.get("role").toString());
        } else if (req.getHeader("request").equals("sign_in")) {
            resp.getWriter().write("sign_in ok, id: '', token: ''");
        } else {
            resp.getWriter().write("error");
        }
    }
}