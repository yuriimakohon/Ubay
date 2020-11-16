package world.ucode.API.pages;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import world.ucode.utils.Utils;
import org.apache.commons.io.IOUtils;
import world.ucode.utils.auction.GetAuctionAPI;
import world.ucode.utils.user.GetUserAPI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@WebServlet("/auction/*")
public class Auction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Utils.getId(req);

        if (id == 0) {
            resp.setStatus(404);
            System.out.println("id == 0");
        } else {
            GetAuctionAPI gaa = new GetAuctionAPI(req);

            if (gaa.status >= 400) {
                resp.setStatus(404);
                System.out.println("error: get gaa");
            } else {
                try {
                    JSONParser jp = new JSONParser();
                    JSONObject jo = (JSONObject) jp.parse(gaa.json);
                    jo = (JSONObject) jp.parse(jo.get("lot").toString());

                    req.setAttribute("lot", jo.toJSONString());

                    GetUserAPI gua = new GetUserAPI(Integer.parseInt(jo.get("sellerId").toString()));
                    if (gua.status >= 400) {
                        resp.setStatus(404);
                        System.out.println("error: get gua");
                    } else {
                        jo = (JSONObject) jp.parse(gua.json);
                        req.setAttribute("user", jo.toJSONString());
                    }
                    // lot
//                    req.setAttribute("lotId", jo.get("lotId"));
//                    req.setAttribute("title", jo.get("title"));
//                    req.setAttribute("price", jo.get("price"));
//                    req.setAttribute("maxPrice", jo.get("maxPrice"));
//                    req.setAttribute("photo", jo.get("photo"));
//                    req.setAttribute("startTime", jo.get("startTime"));
//                    req.setAttribute("duration", jo.get("duration"));
//                    req.setAttribute("desc", jo.get("description"));
//                    req.setAttribute("status", jo.get("status"));
//                    req.setAttribute("bid", jo.get("highestBid"));
//                    req.setAttribute("b_count", jo.get("bidNumber"));
//                    req.setAttribute("p_count", jo.get("photoNumber"));
//                    req.setAttribute("category", jo.get("category"));
//                    req.setAttribute("sellerId", jo.get("sellerId"));

                    // user
//                    req.setAttribute("login", jo.get("login"));
//                    req.setAttribute("avatar", jo.get("avatar"));
                } catch (ParseException | NullPointerException | NumberFormatException e) {
                    resp.setStatus(404);
                    System.out.println("error: " + e.getMessage());
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
            req.getRequestDispatcher("/jsp/auction.jsp").forward(req, resp);
        }
    }
}
