package world.ucode.utils.auction;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import world.ucode.model.db.dao.DAOlot;
import world.ucode.model.db.entetis.Lot;
import world.ucode.model.db.entetis.Users;
import world.ucode.utils.Utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class AuctionUtils {

    public static JSONObject getJSONObject(int id) {
        DAOlot daoLot = new DAOlot();
        Lot lot = daoLot.read(id);

        if (lot == null) {
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        String json;
        JSONObject jo = null;
        JSONParser jp = new JSONParser();

        try {
            json = mapper.writeValueAsString(lot);
            jo = (JSONObject) jp.parse(json);
        } catch(JsonProcessingException | ParseException ignored) {}

        return jo;
    }

    public static boolean deleteAuction(HttpServletResponse resp, int lotId, DAOlot daoLot, Users user) throws IOException {
        Lot lot = daoLot.read(lotId);

        if (lot == null) {
            resp.setStatus(404);
            resp.getWriter().write("lot not found");
            return false;
        }

        if (lot.getSellerId() != user.getId()) {
            resp.setStatus(403);
            resp.getWriter().write("permission denied");
            return false;
        }

        daoLot.delete(lotId);
        return true;
    }

    public static boolean sendLot(HttpServletResponse resp, int lotId, DAOlot daoLot) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Lot lot = daoLot.read(lotId);

        if (lot == null) {
            resp.setStatus(404);
            resp.getWriter().write("lot not found");
            return false;
        }

        if (new Date(lot.getStartTime()).compareTo(new Date()) < 0) {
            lot.setStatus(2);
            daoLot.update(lot);
        }

        resp.setStatus(200);
        resp.getWriter().write(mapper.writeValueAsString(lot));
        return true;
    }

    public static boolean sendLots(HttpServletResponse resp,  DAOlot daoLot) throws IOException {
        List<Lot> listOfLot = daoLot.getAllLots();

        if (listOfLot == null) {
            resp.setStatus(404);
            resp.getWriter().write("lots not found");
            return false;
        }

        resp.setStatus(200);
        resp.getWriter().write(Utils.lotsToJsonArray(listOfLot).toJSONString());
        return true;
    }
}
