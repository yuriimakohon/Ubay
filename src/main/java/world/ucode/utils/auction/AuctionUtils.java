package world.ucode.utils.auction;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import world.ucode.model.db.dao.DAObid;
import world.ucode.model.db.dao.DAOlot;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.model.db.entetis.Bid;
import world.ucode.model.db.entetis.Lot;
import world.ucode.model.db.entetis.Users;
import world.ucode.utils.Interaces.RestUtils;
import world.ucode.utils.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class AuctionUtils implements RestUtils {
    private final DAOlot daoLot;
    private final DAOusers daoUser;
    private final DAObid daoBid;

    public AuctionUtils() {
        daoBid = new DAObid();
        daoLot = new DAOlot();
        daoUser = new DAOusers();
    }

    @Override
    public JSONObject get(int id, HttpServletResponse resp) {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jo = new JSONObject();
        JSONParser jp = new JSONParser();

        Lot lot = daoLot.read(id);

        if (lot == null) {
            resp.setStatus(404);
            jo.put("ok", false);
            return jo;
        }

        if (lot.getStatus() == 1 && new Date(lot.getStartTime()).compareTo(new Date()) < 0) {
            lot.setStatus(2);
            daoLot.update(lot);
        } else if (new Date(lot.getDuration()).compareTo(new Date()) < 0) {
            lot.setStatus(3);
            daoLot.update(lot);
            if (lot.getBidNumber() > 0) {
                Bid bid = daoBid.read(lot.getBidId());
                if (bid != null) {
                    bid.setStatusOfBid(3);
                    daoBid.update(bid);
                }
                Utils.delete_bids_for_lot(lot.getLotId(), daoBid);
            }
        }

        try {
            jo = (JSONObject) jp.parse(mapper.writeValueAsString(lot));
            jo.put("ok", true);
        } catch (IOException | ParseException e) {
            jo.put("ok", false);
        }
        return jo;
    }

    @Override
    public JSONObject create(HttpServletRequest req, HttpServletResponse resp) {
        return null;
    }

    @Override
    public JSONObject put(HttpServletRequest req, HttpServletResponse resp) {
        return null;
    }

    @Override
    public JSONObject delete(int id, HttpServletResponse resp) {
        JSONObject jo = new JSONObject();
        Lot lot = daoLot.read(id);

        if (lot == null) {
            resp.setStatus(404);
            jo.put("error", "lot not found");
            jo.put("ok", false);
            return jo;
        }

        Users user = daoUser.read(lot.getSellerId());

        if (user == null || lot.getSellerId() != user.getId() || lot.getStatus() != 1) {
            resp.setStatus(403);
            jo.put("error", "permission denied");
            jo.put("ok", false);
            return jo;
        }
        daoLot.delete(id);
        jo.put("ok", true);
        return jo;
    }

    @Override
    public JSONArray get_all() {
        List<Lot> lots = daoLot.getAllLots();

        Utils.check_time_of_lot(lots, daoLot, daoBid);
        return Utils.toJsonArray(lots);
    }
}
