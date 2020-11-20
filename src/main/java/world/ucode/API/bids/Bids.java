package world.ucode.API.bids;

import world.ucode.model.db.dao.DAObid;
import world.ucode.model.db.dao.DAOlot;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.model.db.entetis.Bid;
import world.ucode.model.db.entetis.Lot;
import world.ucode.model.db.entetis.Users;
import world.ucode.utils.RequestObject;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/bid/*")
public class Bids extends HttpServlet {
    DAOlot daoLot;
    DAObid daoBid;
    DAOusers daoUser;

    @Override
    public void init(ServletConfig config) throws ServletException {
        daoLot = new DAOlot();
        daoBid = new DAObid();
        daoUser = new DAOusers();
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        RequestObject ro = new RequestObject();
//
//        ro.checkCookie(req.getCookies(), daoUser);
//        ro.checkJson(req);
//
//        if (!ro.ok) {
//            resp.setStatus(406);
//        } else if (ro.user.getUserRole() != 2) {
//            resp.setStatus(403);
//        } else {
//            if (ro.jo.get("lotId") == null || ro.jo.get("bid") == null) {
//                resp.setStatus(406);
//                return;
//            }
//
//            int lotId = Integer.parseInt(ro.jo.get("lotId").toString());
//            double bidPrice = Double.parseDouble(ro.jo.get("bid").toString());
//
//            Lot lot = daoLot.read(lotId);
//            if (lot == null) {
//                resp.setStatus(406);
//                resp.getWriter().write("Lot is not exists");
//            } else if (ro.user.getBalance() < bidPrice) {
//                resp.setStatus(406);
//                resp.getWriter().write("Not enough money");
//            } else if (lot.getHighestBid() >= bidPrice || lot.getPrice() >= bidPrice) {
//                resp.setStatus(406);
//                resp.getWriter().write("Bid is too small");
//            } else if (lot.getBidderId() == ro.user.getId()) {
//                resp.setStatus(406);
//                resp.getWriter().write("You already have bid here");
//            } else  {
//                Users lastBidder = daoUser.read(lot.getBidderId());
//                Users owner = daoUser.read(lot.getSellerId());
//                if (lastBidder != null) {
//                    System.out.println("balance update");
//                    lastBidder.setBalance(lot.getHighestBid() + lastBidder.getBalance());
//                    daoUser.update(lastBidder);
//                }
//                Bid bid = new Bid(lot.getLotId(), lot.getBidderId(), bidPrice);
////                Bid loseBid = daoBid.read();
//
//                if (bidPrice >= lot.getMaxPrice()) {
//                    lot.setStatus(3);
//                    bid.setStatusOfBid(3);
//                    resp.setStatus(201);
//                    owner.setBalance(owner.getBalance() + bidPrice);
//                    daoUser.update(owner);
//                } else {
//                    bid.setStatusOfBid(2);
//                    lot.setStatus(2);
//                    resp.setStatus(200);
//                }
//                lot.setBidNumber(lot.getBidNumber() + 1);
//                lot.setHighestBid(bidPrice);
//                lot.setBidderId(ro.user.getId());
//                ro.user.setBalance(ro.user.getBalance() - bidPrice);
//                daoUser.update(ro.user);
//                daoLot.update(lot);
//            }
//        }

//        RequestObject ro = new RequestObject();
//
//        ro.checkCookie(req.getCookies(), daoUser);
//        ro.checkJson(req);
//
//        if (!ro.ok) {
//            resp.setStatus(406);
//        } else if (ro.user.getUserRole() != 2) {
//            resp.setStatus(403);
//        } else {
//            if (ro.jo.get("lotId") == null || ro.jo.get("bid") == null) {
//                resp.setStatus(406);
//                return;
//            }
//
//            int lotId = Integer.parseInt(ro.jo.get("lotId").toString());
//            double bid = Double.parseDouble(ro.jo.get("bid").toString());
//
//            Lot lot = daoLot.read(lotId);
//            if (lot == null) {
//                resp.setStatus(406);
//                resp.getWriter().write("lot is not exists");
//            } else if (ro.user.getBalance() < bid) {
//                resp.setStatus(406);
//                resp.getWriter().write("not enough money");
//            } else if (lot.getHighestBid() >= bid || lot.getPrice() >= bid) {
//                resp.setStatus(406);
//                resp.getWriter().write("bid is too small");
//            } else if (lot.getBidderId() == ro.user.getId()) {
//                resp.setStatus(406);
//                resp.getWriter().write("you already have bid here");
//            } else  {
//                Users lastBidder = daoUser.read(lot.getBidderId());
//                if (lastBidder != null) {
//                    System.out.println("balance update");
//                    lastBidder.setBalance(lot.getHighestBid() + lastBidder.getBalance());
//                    daoUser.update(lastBidder);
//                }
//
//                if (bid >= lot.getMaxPrice()) {
//                    lot.setStatus(2);
//                    resp.setStatus(201);
//                } else {
//                    lot.setStatus(1);
//                    resp.setStatus(200);
//                }
//                lot.setBidNumber(lot.getBidNumber() + 1);
//                lot.setHighestBid(bid);
//                lot.setBidderId(ro.user.getId());
//                ro.user.setBalance(ro.user.getBalance() - bid);
//                daoUser.update(ro.user);
//                daoLot.update(lot);
//            }
//        }

    }
}
