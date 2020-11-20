package world.ucode.API.pages;

import world.ucode.model.db.dao.DAOusers;
import world.ucode.utils.token.Token;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bids")
public class Bids extends HttpServlet {
    DAOusers daoUser;

    @Override
    public void init() throws ServletException {
        daoUser = new DAOusers();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Token.refreshToken(req, resp, daoUser)) {
            req.getRequestDispatcher("/jsp/bids.jsp").forward(req, resp);
        }
    }
}
