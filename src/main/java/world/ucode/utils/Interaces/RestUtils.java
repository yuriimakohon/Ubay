package world.ucode.utils.Interaces;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface RestUtils {
    JSONObject get(int id, HttpServletResponse resp);
    JSONObject create(HttpServletRequest req, HttpServletResponse resp);
    JSONObject put(HttpServletRequest req, HttpServletResponse resp);
    JSONObject delete(int id, HttpServletResponse resp);
    JSONArray get_all();
}
