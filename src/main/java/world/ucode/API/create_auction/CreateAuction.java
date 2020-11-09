package world.ucode.API.create_auction;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import world.ucode.utils.ParseJson;
import world.ucode.utils.ReadRequestToString;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

@WebServlet("/create_auction")
public class CreateAuction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/create_auction.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = ReadRequestToString.ReadToString(req);
        JSONObject jo = ParseJson.jsonToJsonObject(json);

        resp.setStatus(201);

        if (jo == null) {
            resp.setStatus(499);
            resp.getWriter().write("json not valid");
        }
        assert jo != null;
        JSONArray ja = (JSONArray) jo.get("images");
//        a.setTitle(jo.get("title").toString());
//        a.setDescription(jo.get("desc").toString());
//        a.setStartPrice(Integer.parseInt(jo.get("startPrice").toString()));
//        a.setMaxPrice(Integer.parseInt(jo.get("maxPrice").toString()));
//        a.setStartTime(Long.parseLong(jo.get("startTime").toString()));
//        a.setDuration(Integer.parseInt(jo.get("duration").toString()));
        ObjectMapper mapper = new ObjectMapper();
//        String aJson = mapper.writeValueAsString(a);
//        System.out.println(aJson);
//        resp.getWriter().write(aJson);

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
