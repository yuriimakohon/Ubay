package world.ucode.API.auction.get;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import world.ucode.model.db.dao.DAOlot;
import world.ucode.model.db.entetis.Lot;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/auction/get_all")
public class GetAll extends HttpServlet {
    DAOlot DAOLot;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        DAOLot = new DAOlot();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jo = new JSONObject();
        JSONArray ja = new JSONArray();

        List<Lot> listOfLot = DAOLot.getAllLots();

        for (Lot lot : listOfLot) {
            ja.add(mapper.writeValueAsString(lot));
        }

        jo.put("lots", ja);
        resp.setStatus(200);
        resp.getWriter().write(jo.toJSONString());
    }
}
