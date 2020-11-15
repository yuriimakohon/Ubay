package world.ucode.API;


import world.ucode.model.db.dao.DAOlot;
import world.ucode.model.db.dao.DAOusers;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/search/*")
public class Search extends HttpServlet {
    DAOusers daoUser;
    DAOlot daoLot;

    @Override
    public void init(ServletConfig config) throws ServletException {
        daoLot = new DAOlot();
        daoUser = new DAOusers();
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String by = req.getParameter("by");

        if (by.equals("user")) {

        } else if (by.equals("auction")) {

        } else if (by.equals("bid")) {

        }
    }
}
