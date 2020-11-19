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
        String[] masTitle = mp.get("title");
        String[] masLogin = mp.get("login");
        String[] masStringPrice = mp.get("price");
        String[] masStringRate = mp.get("rate");
        String[] masStringStatus = mp.get("status");
        String title = null;
        String login = null;
        String stringPrice = null;
        String stringRate = null;
        String stringStatus = null;

        int userId = 0;
        double price = 0;
        int rate = 0;
        int status = 0;


        if (masTitle != null) {
            title = masTitle[0];
        }
        if (categories != null) {
            String[] values = categories[0].split("-");
            listCategories = new ArrayList<>();
            Collections.addAll(listCategories, values);
        }
        if (masLogin != null) {
            login = masLogin[0];
            Users user = daoUser.readByLogin(login);
            userId = user.getId();
        }
        if (masStringPrice != null) {
            stringPrice = masStringPrice[0];
            price  = Double.parseDouble(stringPrice);
        }
        if (masStringRate != null) {
            stringRate = masStringRate[0];
            rate = Integer.parseInt(stringRate);
        }
        if (masStringStatus != null) {
            stringStatus = masStringStatus[0];
            String[] stats = stringStatus.split("-");
//            for (String s  : stats) {
//                System.out.println("status: " + s);
//            }
            status = Integer.parseInt(stats[1]);
        }

        System.out.println("title: " + title);
        System.out.println("price: " + price);
        System.out.println("rate: " + rate);
        System.out.println("status: " + status);
        System.out.println("userId: " + userId);

        List<Lot> ll = daoLot.getAllLotsbyCategoris(listCategories, title, price, rate, status, null, userId);

        for (Lot lot : ll) {
            System.out.println(lot.getTitle());
        }
    }
}
