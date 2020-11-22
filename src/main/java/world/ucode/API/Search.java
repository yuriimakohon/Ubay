package world.ucode.API;

import world.ucode.model.db.dao.DAOlot;
import world.ucode.model.db.entetis.Lot;
import world.ucode.utils.Utils;

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
    private DAOlot daoLot;

    @Override
    public void init(ServletConfig config) throws ServletException {
        daoLot = new DAOlot();
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
        String[] masStringId = mp.get("user_id");
        String title = null;
        String login = null;
        String stringStatus = null;

        double price = 0;
        int rate = 0;
        int status = 0;
        int userId = 0;

        if (masStringId != null) {
            userId = Integer.parseInt(masStringId[0]);
        }
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
        }
        if (masStringPrice != null) {
            price  = Double.parseDouble(masStringPrice[0]);
        }
        if (masStringRate != null) {
            rate = Integer.parseInt(masStringRate[0]);
        }
        if (masStringStatus != null) {
            stringStatus = masStringStatus[0];
            String[] stats = stringStatus.split("-");
            status = Integer.parseInt(stats[1]);
        }

        List<Lot> ll = daoLot.getAllLotsbyCategoris(listCategories, title, price, rate, status, login, userId);

        resp.setStatus(200);
        resp.getWriter().write(Utils.toJsonArray(ll).toJSONString());
    }
}
