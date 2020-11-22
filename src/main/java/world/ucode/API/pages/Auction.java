package world.ucode.API.pages;

import org.json.simple.JSONObject;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.utils.Bid.BidUtils;
import world.ucode.utils.user.UserUtils;
import world.ucode.model.db.dao.DAOfeedback;
import world.ucode.model.db.entetis.Feedback;
import world.ucode.utils.ParseCookie;
import world.ucode.utils.Utils;
import world.ucode.utils.auction.AuctionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/auction/*")
public class Auction extends HttpServlet {
    BidUtils utils;
    AuctionUtils auctionUtils;
    UserUtils userUtils;

    @Override
    public void init() {
        userUtils = new UserUtils();
        utils = new BidUtils();
        auctionUtils = new AuctionUtils();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Utils.getId(req);

        if (id == 0) {
            resp.setStatus(404);
            System.out.println("id == 0");
        } else {
            JSONObject lot = auctionUtils.get(id, resp);

            if (lot == null) {
                resp.setStatus(404);
                System.out.println("not found lot");
            } else {
                req.setAttribute("lot", lot.toJSONString());
                JSONObject userSeller = userUtils.get(Integer.parseInt(lot.get("sellerId").toString()), resp);

                if (userSeller == null) {
                    resp.setStatus(404);
                    System.out.println("seller not found");
                } else {
                    req.setAttribute("user", userSeller.toJSONString());
                }

                JSONObject bid;
                if (lot.get("bidId").toString().equals("0")) {
                    bid = new JSONObject();
                    bid.put("price", "0");
                    bid.put("ok", false);
                } else {
                    bid = utils.get(Integer.parseInt(lot.get("bidId").toString()), resp);
                }

                if (bid.get("ok").equals("false")) {
                    resp.setStatus(404);
                    resp.getWriter().write(bid.toJSONString());
                } else {
                    req.setAttribute("bid", bid.toJSONString());

                    if (bid.get("bidderId") != null) {
                        int idBidder = Integer.parseInt(bid.get("bidderId").toString());
                        if (idBidder != 0) {
                            JSONObject userBidder = userUtils.get(Integer.parseInt(bid.get("bidderId").toString()), resp);
                            req.setAttribute("userBidder", userBidder.toJSONString());
                        }
                    }
                }
            }
        }
        if (resp.getStatus() == 404) {
            String errorPath = req.getServletPath();
            if (req.getPathInfo() != null) {
                errorPath += req.getPathInfo();
            }
            req.setAttribute("path", errorPath);
            req.getRequestDispatcher("/jsp/404.jsp").forward(req, resp);
        } else {
            String idVisitor = ParseCookie.parseToMap(req.getCookies()).get("id");
            req.setAttribute("canFeedback", false);
            Feedback fb = new DAOfeedback().get_by_user_lot(Integer.parseInt(idVisitor), id);
            if (fb == null)
                req.setAttribute("canFeedback", true);
            req.getRequestDispatcher("/jsp/auction.jsp").forward(req, resp);
        }
    }
}
