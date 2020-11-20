package world.ucode.API.pages;

import world.ucode.model.db.dao.DAOusers;
import world.ucode.utils.token.Token;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/account")
public class Account extends HttpServlet {
    DAOusers daoUser;

    @Override
    public void init() {
        daoUser = new DAOusers();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Token.refreshToken(req, resp, daoUser)) {
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/jsp/account.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
