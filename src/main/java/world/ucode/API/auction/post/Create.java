package world.ucode.API.auction.post;

import org.json.simple.JSONArray;
import world.ucode.model.db.dao.DAOlot;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.utils.RequestObject;
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

@WebServlet("/auction/create")
public class Create extends HttpServlet {
    DAOlot DAOLot;
    DAOusers DAOUser;

    @Override
    public void init(ServletConfig config) throws ServletException {
        DAOLot = new DAOlot();
        DAOUser = new DAOusers();
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        RequestObject ro = new RequestObject();

        ro.checkCookie(req.getCookies(), DAOUser);
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
        DAOLot.create(va.lot);

        String path = "src/main/webapp/resources/";
        File user_dir = new File(path + ro.user.getId());
        File lot_dir = new File(path + ro.user.getId() + "/" + va.lot.getLotId());

        path += ro.user.getId() + "/" + va.lot.getLotId();
        va.lot.setPhoto("resources/" + ro.user.getId() + "/" + va.lot.getLotId() + "/");
        DAOLot.update(va.lot);

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
}
