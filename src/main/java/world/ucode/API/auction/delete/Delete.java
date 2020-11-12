package world.ucode.API.auction.delete;


import world.ucode.model.db.dao.DAOlot;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.utils.RequestObject;
import world.ucode.utils.auction.ValidatorAuction;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/auction/delete")
public class Delete extends HttpServlet {
    DAOusers daoUser;
    DAOlot daoLot;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        daoUser = new DAOusers();
        daoLot = new DAOlot();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestObject ro = new RequestObject();
        ValidatorAuction va = new ValidatorAuction();

        int lotId = va.readParamLotId(req);

        ro.checkCookie(req.getCookies(), daoUser);

        if (!ro.ok || lotId == -1) {
            resp.setStatus(406);
            resp.getWriter().write("validation fail");
            return;
        }

        daoLot.delete(lotId);
    }
}
