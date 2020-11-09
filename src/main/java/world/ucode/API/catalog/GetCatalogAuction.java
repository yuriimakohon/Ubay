package world.ucode.API.catalog;

import org.json.simple.JSONObject;
import world.ucode.utils.ParseJson;
import world.ucode.utils.ReadRequestToString;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/catalog/get_info")
public class GetCatalogAuction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = ReadRequestToString.ReadToString(req);
        JSONObject joReq = ParseJson.jsonToJsonObject(json);

        System.out.println(json);
        resp.setStatus(200);
        resp.getWriter().write("nine, well done");
    }
}
