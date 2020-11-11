package world.ucode.API.auction.get;


import world.ucode.model.db.dao.DAOlot;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.model.db.entetis.Lot;
import world.ucode.utils.ParseJson;
import world.ucode.utils.RequestObject;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/get_auction")
public class Get extends HttpServlet {
    DAOlot daoLot;
    DAOusers daoUser;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        daoLot = new DAOlot();
        daoUser = new DAOusers();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestObject ro = new RequestObject();

        ro.checkCookie(req.getCookies(), daoUser);

        if (!ro.ok) {
            resp.setStatus(406);
            resp.getWriter().write(ro.getResp());
            return;
        }

        int lotId = Integer.parseInt(req.getParameter("lotId"));

        Lot lot = daoLot.read(lotId);
        if (lot == null) {
            resp.setStatus(406);
            resp.getWriter().write("what is wrong ?");
        } else {
            resp.setStatus(200);
            resp.getWriter().write(ParseJson.lotToJson(lot));
        }
    }
}
