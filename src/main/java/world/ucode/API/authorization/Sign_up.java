package world.ucode.API.authorization;

import world.ucode.model.db.dao.DAOusers;
import world.ucode.model.db.entetis.Users;
import world.ucode.utils.token.Token;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign_up")
public class Sign_up extends HttpServlet {
    private DAOusers user;

    public void init() {
        user = new DAOusers();
        System.out.println("init serv");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users myuser  = user.readbyLogin(req.getParameter("login"));
        if (myuser == null) {
            myuser = new Users(new Token().getToken(), req.getParameter("login"));
            user.create(myuser);
            System.out.println("not user");
        }
        else
            System.out.println("yes user");
        resp.setStatus(200);
        System.out.println(req.getParameter("login"));
        System.out.println(req.getParameter("password"));
        System.out.println(req.getParameter("role"));
        System.out.println(new Token().getToken());
    }
}