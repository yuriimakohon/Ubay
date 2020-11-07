package world.ucode.API.authorization;

import world.ucode.utils.token.Token;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign_up")
public class Sign_up extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        System.out.println(req.getParameter("login"));
        System.out.println(req.getParameter("password"));
        System.out.println(req.getParameter("role"));
        System.out.println(new Token().getToken());
    }
}