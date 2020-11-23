package world.ucode.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
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

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Utils {
    public static <T1, T2> void printHashMap(HashMap<T1, T2> hm) {
        for (Map.Entry<T1, T2> entry : hm.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    public static boolean checkValidLogin(String login) {
        return login == null || !RegExp.checkRegExp("^[A-Za-z0-9]{3,21}$", login);
    }
    public static boolean checkValidPassword(String password) {
        return password == null || !RegExp.checkRegExp("^[a-z0-9]{128}$", password);
    }

    public static int getId(HttpServletRequest req) {
        String pathInfo = req.getPathInfo();

        if (pathInfo == null || pathInfo.equals("") || pathInfo.equals("/")) {
            return 0;
        }
        String[] params = req.getPathInfo().split("/");
        String param = params[params.length-1];

        int lotId;
        try {
            lotId = Integer.parseInt(param);
        } catch (NumberFormatException e) {
            return -1;
        }
        return lotId;
    }

    public static boolean checkValidRole(String role) {
        return role == null || !RegExp.checkRegExp("^[0-9]{1,5}$", role);
    }

    public static <T> JSONArray toJsonArray(List<T> objs)  {
        ObjectMapper mapper = new ObjectMapper();
        JSONArray ja = new JSONArray();
        JSONParser jp = new JSONParser();
        JSONObject jo;
        String json;

        if (objs != null) {
            for (T obj : objs) {
                try {
                    json = mapper.writeValueAsString(obj);
                    jo = (JSONObject) jp.parse(json);
                    ja.add(jo);
                } catch (ParseException | JsonProcessingException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return ja;
    }

    public static void check_time_of_lot(List<Lot> lots, DAOlot daoLot, DAObid daoBid) {
        if (lots != null) {
            for (Lot lot : lots) {
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
                        delete_bids_for_lot(lot.getLotId(), daoBid);
                    }
                }
            }
        }
    }

    public static void delete_bids_for_lot(int lot_id, DAObid daoBid) {
        List<Bid> bids = daoBid.get_all_by_lot(lot_id);

        for (Bid bid : bids) {
            if (bid.getStatusOfBid() != 3) {
                delete_bid(bid, daoBid);
            }
        }
    }

    public static void delete_bid(Bid bid, DAObid daoBid) {
        DAOusers daoUser = new DAOusers();

        Users user = daoUser.read(bid.getBidderId());
        if (user != null) {
            user.setBalance(user.getBalance() + bid.getPrice());
        }
        daoBid.delete(bid.getId());
    }
}
