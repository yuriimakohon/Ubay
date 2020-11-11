package world.ucode.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import world.ucode.model.db.entetis.Lot;

import java.util.List;

public class ParseJson {
    public static JSONObject jsonToJsonObject(String json) {
        JSONParser parser = new JSONParser();
        JSONObject jo;

        try {
            jo = (JSONObject) parser.parse(String.valueOf(json));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            jo = null;
        }

        return jo;
    }
    public static String lotsToJson(List<Lot> listOfLot) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jo = new JSONObject();
        JSONArray ja = new JSONArray();

        for (Lot lot : listOfLot) {
            ja.add(mapper.writeValueAsString(lot));
        }
        jo.put("lots", ja);
        return jo.toJSONString();
    }

    public static String lotToJson(Lot lot) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jo = new JSONObject();

        jo.put("lot", mapper.writeValueAsString(lot));
        return jo.toJSONString();
    }
}
