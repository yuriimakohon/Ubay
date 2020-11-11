package world.ucode.API.user.put;


import world.ucode.model.db.dao.DAOusers;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/change_balance")
public class ChangeBalance extends HttpServlet {
    DAOusers daoUser;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        daoUser = new DAOusers();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("change balance");
    }
}
