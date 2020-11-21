package world.ucode.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import world.ucode.model.db.dao.DAObid;
import world.ucode.model.db.entetis.Bid;

public class BidUtils {
    public static JSONObject getJSONObject(int id) {
        DAObid daoBid = new DAObid();
        Bid bid = daoBid.read(id);

        if (bid == null) {
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        String json;
        JSONObject jo = null;
        JSONParser jp = new JSONParser();

        try {
            json = mapper.writeValueAsString(bid);
            jo = (JSONObject) jp.parse(json);
        } catch(JsonProcessingException | ParseException ignored) {}

        return jo;
    }
}
