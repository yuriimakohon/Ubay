package world.ucode.API.pages;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.utils.ParseCookie;
import world.ucode.utils.RequestObject;
import world.ucode.utils.Utils;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@WebServlet("/auction/*")
public class Auction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
        String path = req.getContextPath();

        if (path == null || path.equals("null")) {
            System.out.println("here");
            return;
        }
        System.out.println("path: " + path);

        int id = Utils.getId(req);

        URL url = new URL("http://localhost:8080/api/auction/" + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("method", "GET");
//        connection.setRequestProperty("credentials", "same-origin");

        InputStream response = connection.getInputStream();

        StringWriter writer = new StringWriter();
        IOUtils.copy(response,  writer, StandardCharsets.UTF_8.name());
        connection.disconnect();

        String json = writer.toString();
        System.out.println(json);

        JSONParser jp = new JSONParser();
        JSONObject jo = null;

        try {
            jo = (JSONObject) jp.parse(json);
            String s =  jo.get("lot").toString();
            jo = (JSONObject) jp.parse(s);
            req.setAttribute("lotId", jo.get("lotId"));
            req.setAttribute("title", jo.get("title"));
            req.setAttribute("price", jo.get("price"));
            req.setAttribute("maxPrice", jo.get("maxPrice"));
            req.setAttribute("photo", jo.get("photo"));
            req.setAttribute("startTime", jo.get("startTime"));
            req.setAttribute("duration", jo.get("duration"));
            req.setAttribute("desc", jo.get("description"));
            req.setAttribute("status", jo.get("status"));
            req.setAttribute("bid", jo.get("highestBid"));
            req.setAttribute("b_count", jo.get("bidNumber"));
            req.setAttribute("p_count", jo.get("photoNumber"));
            req.setAttribute("category", jo.get("category"));
            req.setAttribute("sellerId", jo.get("sellerId"));
            URL urlSeller = new URL("http://localhost:8080/api/user/" + jo.get("sellerId"));
            HttpURLConnection connectionSeller = (HttpURLConnection) urlSeller.openConnection();
            connectionSeller.setRequestProperty("method", "GET");
            InputStream responseSeller = connectionSeller.getInputStream();

            StringWriter writerSeller = new StringWriter();
            IOUtils.copy(responseSeller,  writerSeller, StandardCharsets.UTF_8.name());
            connectionSeller.disconnect();

            String jsonSeller = writerSeller.toString();
            System.out.println(jsonSeller);
            jo = (JSONObject) jp.parse(jsonSeller);
            req.setAttribute("login", jo.get("login"));
            req.setAttribute("avatar", jo.get("avatar"));
        } catch (ParseException | NullPointerException e) {
            System.out.println(e.getMessage());
            resp.setStatus(404);
        }
        req.getRequestDispatcher("/jsp/auction.jsp").forward(req, resp);
    }
}
