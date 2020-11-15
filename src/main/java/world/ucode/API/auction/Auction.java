package world.ucode.API.auction.get;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import world.ucode.model.db.dao.DAOlot;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.model.db.entetis.Lot;
import world.ucode.utils.ParseJson;
import world.ucode.utils.RequestObject;
import world.ucode.utils.Utils;
import world.ucode.utils.auction.ValidatorAuction;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@WebServlet("/api/auction/*")
public class Auction extends HttpServlet {
    DAOlot daoLot;
    DAOusers daoUser;

    @Override
    public void init(ServletConfig config) throws ServletException {
        daoLot = new DAOlot();
        daoUser = new DAOusers();
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        RequestObject ro = new RequestObject();
        int lotId = Utils.getId(req);
        resp.setContentType("application/json;charset=utf-8");

        ro.checkCookie(req.getCookies(), daoUser);

        if (!ro.ok) {
            resp.setStatus(406);
            resp.getWriter().write("you must have permission");
            return;
        }

        if (lotId == -1) {
            ObjectMapper mapper = new ObjectMapper();
            JSONObject jo = new JSONObject();
            JSONArray ja = new JSONArray();

            if (daoLot == null) {
                System.out.println("daoLot null");
            }

            List<Lot> listOfLot = daoLot.getAllLots();

            if (listOfLot == null) {
                System.out.println("lots null");
                return;
            }

            for (Lot lot : listOfLot) {
                ja.add(mapper.writeValueAsString(lot));
            }

            jo.put("lots", ja);
            resp.setStatus(200);
            resp.getWriter().write(jo.toJSONString());
            return;
        }
        
        Lot lot = daoLot.read(lotId);
        if (lot == null) {
            resp.setStatus(406);
            resp.getWriter().write("lot not found");
        } else {
            resp.setStatus(200);
            resp.getWriter().write(ParseJson.lotToJson(lot));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        RequestObject ro = new RequestObject();

        ro.checkCookie(req.getCookies(), daoUser);
        ro.checkJson(req);

        if (!ro.ok) {
            resp.setStatus(ro.getStatus());
            resp.getWriter().write(ro.getResp());
            return;
        }

        ValidatorAuction va = new ValidatorAuction();
        if (!va.validatorAuction(ro.jo)) {
            resp.setStatus(406);
            resp.getWriter().write("validation fail");
            return;
        }
        va.lot.setSellerId(ro.user.getId());
        daoLot.create(va.lot);

        String path = "src/main/webapp/resources/";
        File user_dir = new File(path + ro.user.getId());
        File lot_dir = new File(path + ro.user.getId() + "/" + va.lot.getLotId());

        path += ro.user.getId() + "/" + va.lot.getLotId();
        va.lot.setPhoto("resources/" + ro.user.getId() + "/" + va.lot.getLotId() + "/");
        daoLot.update(va.lot);

        if (!user_dir.exists()) {
            user_dir.mkdir();
        }
        if (!lot_dir.exists()) {
            lot_dir.mkdir();
        }

        JSONArray ja = (JSONArray) ro.jo.get("images");
        int i = 0;
        for (String s : (Iterable<String>) ja) {
            File f = new File(path + "/" + i +".jpg");
            for (;f.exists(); i++) {
                f = new File(path + "/" + i + ".jpg");
            }
            byte[] data = Base64.getDecoder().decode(s.split(",")[1]);
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(data);
            i++;
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestObject ro = new RequestObject();

        ro.checkCookie(req.getCookies(), daoUser);
        ro.checkJson(req);

        if (!ro.ok) {
            resp.setStatus(ro.getStatus());
            resp.getWriter().write("validation fail");
            return;
        }

        ValidatorAuction va = new ValidatorAuction();
        int lotId = Utils.getId(req);

        if (!va.validatorAuction(ro.jo) || lotId == -1) {
            resp.setStatus(406);
            resp.getWriter().write("validation fail");
            return;
        }

        Lot lot = daoLot.read(lotId);

        if (ro.user.getId() != lot.getSellerId()) {
            resp.setStatus(406);
            resp.getWriter().write("permission denied");
            return;
        }

        lot.setTitle(va.lot.getTitle());
        lot.setDescription(va.lot.getDescription());
        lot.setPrice(va.lot.getPrice());
        lot.setMaxPrice(va.lot.getMaxPrice());
        lot.setStartTime(va.lot.getStartTime());
        lot.setDuration(va.lot.getDuration());
        daoLot.update(lot);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestObject ro = new RequestObject();
        ValidatorAuction va = new ValidatorAuction();

        int lotId = Utils.getId(req);

        ro.checkCookie(req.getCookies(), daoUser);

        if (!ro.ok || lotId == -1) {
            resp.setStatus(406);
            resp.getWriter().write("validation fail");
            return;
        }

        daoLot.delete(lotId);
    }
}

