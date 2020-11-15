package world.ucode.API.pages;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import world.ucode.utils.Utils;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@WebServlet("/auction/*")
public class Auction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Utils.getId(req);

        System.out.println(req.getPathInfo());

        URL url = new URL("http://localhost:8080/api/auction/" + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("method", "GET");
        connection.setRequestProperty("credentials", "same-origin");

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
            req.setAttribute("title", jo.get("title"));
            req.setAttribute("price", jo.get("price"));
            req.setAttribute("maxPrice", jo.get("maxPrice"));
            req.setAttribute("photo", jo.get("photo"));
            req.setAttribute("startTime", jo.get("startTime"));
            req.setAttribute("duration", jo.get("duration"));
            req.setAttribute("desc", jo.get("description"));
            req.setAttribute("status", jo.get("status"));
            req.setAttribute("bid", jo.get("highestBid"));
            req.setAttribute("b_count", jo.get("bidnumber"));
//            req.setAttribute("p_count", jo.get("photoNumber"));
            req.setAttribute("category", jo.get("category"));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            resp.setStatus(404);
        }
        req.getRequestDispatcher("/jsp/auction.jsp").forward(req, resp);
    }
}
