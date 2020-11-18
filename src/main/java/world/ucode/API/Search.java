package world.ucode.API;


import world.ucode.model.db.dao.DAOlot;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.model.db.entetis.Lot;
import world.ucode.model.db.entetis.Users;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@WebServlet("/api/search/*")
public class Search extends HttpServlet {
    private DAOusers daoUser;
    private DAOlot daoLot;

    @Override
    public void init(ServletConfig config) throws ServletException {
        daoLot = new DAOlot();
        daoUser = new DAOusers();
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> mp = req.getParameterMap();

        List<String> listCategories = null;
        String[] categories = mp.get("categories");
        String title = mp.get("title")[0];
        String login = mp.get("login")[0];
        String stringPrice = mp.get("price")[0];
        String stringRate = mp.get("rate")[0];


        int userId = 0;
        double price = 0;


        if (categories != null) {
            String[] values = categories[0].split("-");
            listCategories = new ArrayList<>();
            Collections.addAll(listCategories, values);
        }
        if (login != null) {
            Users user = daoUser.readByLogin(login);
            userId = user.getId();
        }
        if (stringPrice != null) {
            price  = Double.parseDouble(stringPrice);
        }

        List<Lot> ll = daoLot.getAllLotsbyCategoris(categories, title, price, rate, status, null, userId);



        for (Map.Entry<String, String[]> entry : mp.entrySet()) {
            System.out.print("key: " + entry.getKey() + "\nvalues: ");
            String[] values = entry.getValue()[0].split("-");
            Collections.addAll(categories, values);
            System.out.println();
        }
    }
}
