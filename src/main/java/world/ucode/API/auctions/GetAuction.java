package world.ucode.API.auctions;


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

@WebServlet("/edit/get_auction")
public class GetAuction extends HttpServlet {
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
        ro.checkJson(req);

        if (!ro.ok || ro.user.getUserRole() == 2) {
            resp.setStatus(406);
            resp.getWriter().write(ro.getResp());
            return;
        }

        String sLotId = ro.jo.get("lotId").toString();
        int lotId = Integer.parseInt(sLotId);

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
