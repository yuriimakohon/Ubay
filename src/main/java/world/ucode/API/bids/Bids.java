package world.ucode.API.bids;

import org.json.simple.JSONObject;
import world.ucode.model.db.dao.DAObid;
import world.ucode.model.db.dao.DAOlot;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.model.db.entetis.Bid;
import world.ucode.model.db.entetis.Lot;
import world.ucode.model.db.entetis.Users;
import world.ucode.utils.Bid.BidUtils;
import world.ucode.utils.RequestObject;
import world.ucode.utils.Utils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/bid/*")
public class Bids extends HttpServlet {
    DAOlot daoLot;
    DAObid daoBid;
    DAOusers daoUser;
    BidUtils utils;

    @Override
    public void init(ServletConfig config) throws ServletException {
        daoLot = new DAOlot();
        daoBid = new DAObid();
        daoUser = new DAOusers();
        utils = new BidUtils();
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        resp.setContentType("application/json;charset=utf-8");
        RequestObject ro = new RequestObject();
        int id = Utils.getId(req);

        ro.checkCookie(req.getCookies(), daoUser);

        if (!ro.ok || ro.user.getUserRole() != 2) {
            resp.setStatus(403);
            resp.getWriter().write("permission denied");
            return;
        }

        if (id == 0) {
            resp.setStatus(200);
            resp.getWriter().write(utils.get_by_user(ro.user.getId()).toJSONString());
        } else if (id == -1) {
            resp.setStatus(404);
            resp.getWriter().write("not found");
        } else if (id > 0) {
            resp.getWriter().write(utils.get(id, resp).toJSONString());
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=utf-8");
        RequestObject ro = new RequestObject();

        ro.checkCookie(req.getCookies(), daoUser);
        ro.checkJson(req);

        if (!ro.ok) {
            resp.setStatus(406);
        } else if (ro.user.getUserRole() != 2) {
            resp.setStatus(403);
        } else {
            if (ro.jo.get("lotId") == null || ro.jo.get("bid") == null) {
                resp.setStatus(406);
                return;
            }

            int lotId = Integer.parseInt(ro.jo.get("lotId").toString());
            double bidPrice = Double.parseDouble(ro.jo.get("bid").toString());

            Lot lot = daoLot.read(lotId);
            if (lot == null) {
                resp.setStatus(406);
                resp.getWriter().write("Lot is not exists");
                return;
            }

            Bid lastBid = daoBid.read(lot.getBidId());
            Users owner = daoUser.read(lot.getSellerId());
            if (ro.user.getBalance() < bidPrice) {
                resp.setStatus(406);
                resp.getWriter().write("Not enough money");
                return;
            }
            if ((lastBid != null && lastBid.getPrice() >= bidPrice) || lot.getPrice() >= bidPrice) {
                resp.setStatus(406);
                resp.getWriter().write("Bid is too small");
            } else if ( lastBid != null && lastBid.getBidderId() == ro.user.getId()) {
                resp.setStatus(406);
                resp.getWriter().write("You already have bid here");
            } else {
                Bid bid = new Bid(lot.getLotId(), ro.user.getId(), bidPrice);
                if (bidPrice >= lot.getMaxPrice()) {
                    lot.setStatus(3);
                    bid.setStatusOfBid(3);
                    resp.setStatus(201);
                    owner.setBalance(owner.getBalance() + bidPrice);
                    daoUser.update(owner);

                    Utils.delete_bids_for_lot(lotId, daoBid);
                } else {
                    if (lastBid != null) {
                        lastBid.setStatusOfBid(2);
                        daoBid.update(lastBid);
                    }
                    bid.setStatusOfBid(1);
                    lot.setStatus(2);
                    resp.setStatus(200);
                }
                daoBid.create(bid);
                lot.setBidNumber(lot.getBidNumber() + 1);
                lot.setBidId(bid.getId());
                ro.user.setBalance(ro.user.getBalance() - bidPrice);
                daoUser.update(ro.user);
                daoLot.update(lot);
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        RequestObject ro = new RequestObject();
        int id = Utils.getId(req);

        ro.checkCookie(req.getCookies(), daoUser);
        if (!ro.ok || id <= 0) {
            resp.setStatus(403);
            resp.getWriter().write("permission denied");
            return;
        }

        JSONObject jo = utils.delete(id, resp);

        resp.getWriter().write(jo.toJSONString());
    }
}
