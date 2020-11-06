package world.ucode.API.create_auctions;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

@WebServlet("/create_auctions")
public class CreateAuctions extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/create_auctions.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(201);
        resp.setContentType("text/plain");
        resp.getWriter().write("Hello World!");

        BufferedReader bf = req.getReader();
        String buff = null;
        StringBuilder json = new StringBuilder();

        while ((buff = bf.readLine()) != null) {
            json.append(buff);
        }
        System.out.println(json);

        JSONParser jp = new JSONParser();
        JSONObject jo = null;

        try {
            jo = (JSONObject) jp.parse(String.valueOf(json));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assert jo != null;
        JSONArray ja = (JSONArray) jo.get("images");
        int i = 0;

        for (String s : (Iterable<String>) ja) {
            byte[] data = Base64.getDecoder().decode(s.split(",")[1]);
            File f = new File(i +".jpg");
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(data);
            i++;
        }
    }
}
