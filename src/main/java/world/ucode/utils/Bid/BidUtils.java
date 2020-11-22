package world.ucode.utils.Bid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import world.ucode.model.db.dao.DAObid;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.model.db.entetis.Bid;
import world.ucode.model.db.entetis.Users;
import world.ucode.utils.Interaces.GetByUser;
import world.ucode.utils.Interaces.RestUtils;
import world.ucode.utils.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BidUtils implements RestUtils, GetByUser {
    DAObid daoBid;
    DAOusers daoUsers;

    public BidUtils() {
        daoBid = new DAObid();
        daoUsers = new DAOusers();
    }

    @Override
    public JSONObject get(int id, HttpServletResponse resp) {
        DAObid daoBid = new DAObid();
        Bid bid = daoBid.read(id);
        JSONObject jo = new JSONObject();

        if (bid == null) {
            jo.put("ok", false);
            resp.setStatus(404);

            return jo;
        }

        ObjectMapper mapper = new ObjectMapper();
        JSONParser jp = new JSONParser();

        try {
            jo = (JSONObject) jp.parse(mapper.writeValueAsString(bid));
        } catch(JsonProcessingException | ParseException ignored) {}

        resp.setStatus(200);
        jo.put("ok", true);
        return jo;
    }

    @Override
    public JSONObject create(HttpServletRequest req, HttpServletResponse resp) {
        return new JSONObject();
    }

    @Override
    public JSONObject delete(int id, HttpServletResponse resp) {
        JSONObject jo = new JSONObject();

        Bid bid = daoBid.read(id);
        if (bid == null || bid.getStatusOfBid() != 2) {
            resp.setStatus(403);
            jo.put("ok", false);
        } else {
            Users user =  daoUsers.read(bid.getBidderId());
            user.setBalance(user.getBalance() + bid.getPrice());
            daoUsers.update(user);

            daoBid.delete(id);
            resp.setStatus(200);
            jo.put("ok", true);
        }
        System.out.println(jo.toJSONString());
        return jo;
    }


    @Override
    public JSONObject put(HttpServletRequest req, HttpServletResponse resp) {
        return new JSONObject();
    }

    @Override
    public JSONArray get_all() {
        return Utils.toJsonArray(daoBid.get_all());
    }

    @Override
    public JSONArray get_by_user(int id) {
        return Utils.toJsonArray(daoBid.get_all_by_user(id));
    }
}
