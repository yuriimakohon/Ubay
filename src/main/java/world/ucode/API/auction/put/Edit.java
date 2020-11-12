package world.ucode.API.auction.put;

import world.ucode.model.db.dao.DAOlot;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.model.db.entetis.Lot;
import world.ucode.utils.RequestObject;
import world.ucode.utils.auction.ValidatorAuction;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/auction/edit")
public class Edit extends HttpServlet {
    DAOlot daoLot;
    DAOusers daoUser;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        daoUser = new DAOusers();
        daoLot = new DAOlot();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestObject ro = new RequestObject();

        ro.checkCookie(req.getCookies(), daoUser);
        ro.checkJson(req);

        if (!ro.ok) {
            resp.setStatus(ro.getStatus());
            resp.getWriter().write(ro.getResp());
            return;
        }

        ValidatorAuction va = new ValidatorAuction();
        int lotId = va.readParamLotId(req);

        if (!va.validatorAuction(ro.jo) || lotId == -1) {
            resp.setStatus(406);
            resp.getWriter().write("validation fail");
            return;
        }

        Lot lot = daoLot.read(lotId);

        if (ro.user.getId() != lot.getSellerId()) {
            resp.setStatus(406);
            resp.getWriter().write("permission denied");
            return;
        }

        lot.setTitle(va.lot.getTitle());
        lot.setDescription(va.lot.getDescription());
        lot.setPrice(va.lot.getPrice());
        lot.setMaxPrice(va.lot.getMaxPrice());
        lot.setStartTime(va.lot.getStartTime());
        lot.setDuration(va.lot.getDuration());
        daoLot.update(lot);
    }
}
