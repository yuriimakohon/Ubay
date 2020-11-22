package world.ucode.API.pages;

import org.json.simple.JSONObject;
import world.ucode.model.db.dao.DAObid;
import world.ucode.model.db.dao.DAOfeedback;
import world.ucode.model.db.dao.DAOlot;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.model.db.entetis.Lot;
import world.ucode.utils.BidUtils;
import world.ucode.utils.ParseCookie;
import world.ucode.utils.UserUtils;
import world.ucode.utils.Utils;
import world.ucode.utils.auction.AuctionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/auction/*")
public class Auction extends HttpServlet {
    DAOusers daoUser;
    DAOlot daoLot;
    DAObid daoBid;

    @Override
    public void init() {
        daoBid = new DAObid();
        daoLot = new DAOlot();
        daoUser = new DAOusers();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Utils.getId(req);

        if (id == 0) {
            resp.setStatus(404);
            System.out.println("id == 0");
        } else {
            JSONObject lot = AuctionUtils.getJSONObject(id);

            if (lot == null) {
                resp.setStatus(404);
                System.out.println("not found lot");
            } else {
                req.setAttribute("lot", lot.toJSONString());

                System.out.println(Integer.parseInt(lot.get("sellerId").toString()));
                JSONObject user = UserUtils.getJSONObject(Integer.parseInt(lot.get("sellerId").toString()));

                if (user == null) {
                    resp.setStatus(404);
                    System.out.println("seller not found");
                } else {
                    req.setAttribute("user", user.toJSONString());
                }

                JSONObject bid;
                if (lot.get("bidId").toString().equals("0")) {
                    bid = new JSONObject();
                    bid.put("price", "0");
                } else {
                    bid = BidUtils.getJSONObject(Integer.parseInt(lot.get("bidId").toString()));
                }

                if (bid == null) {
                    resp.setStatus(404);
                    System.out.println("bid not found");
                } else {
                    req.setAttribute("bid", bid.toJSONString());
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
            String idCommentator;
            if ((idCommentator = ParseCookie.parseToMap(req.getCookies()).get("id")) != null) {
                if (new DAOfeedback().get_by_user_lot(Integer.parseInt(idCommentator), id) == null)
                    req.setAttribute("canFeedback", true);
                else
                    req.setAttribute("canFeedback", false);
            }
            req.getRequestDispatcher("/jsp/auction.jsp").forward(req, resp);
        }
    }
}
