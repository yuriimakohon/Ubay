package world.ucode.API.auction.get;


import world.ucode.model.db.dao.DAOlot;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.model.db.entetis.Lot;
import world.ucode.utils.ParseJson;
import world.ucode.utils.RequestObject;
import world.ucode.utils.auction.ValidatorAuction;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/get_auction")
@WebServlet("/auction/*")
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("method get");
//        RequestObject ro = new RequestObject();
//        ValidatorAuction va = new ValidatorAuction();
//        int lotId = va.readParamLotId(req);
//
//        ro.checkCookie(req.getCookies(), daoUser);
//
//        if (!ro.ok || lotId == -1) {
//            resp.setStatus(406);
//            resp.getWriter().write(ro.getResp());
//            return;
//        }
//
//        Lot lot = daoLot.read(lotId);
//        if (lot == null) {
//            resp.setStatus(406);
//            resp.getWriter().write("lot not found");
//        } else {
//            resp.setStatus(200);
//            resp.getWriter().write(ParseJson.lotToJson(lot));
//        }
    }
}
