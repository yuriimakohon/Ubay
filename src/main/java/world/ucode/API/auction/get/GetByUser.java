package world.ucode.API.auction.get;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.model.db.entetis.Lot;
import world.ucode.model.db.entetis.Users;
import world.ucode.utils.ParseJson;
import world.ucode.utils.RequestObject;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/get_my_auctions")
public class GetByUser extends HttpServlet {
    DAOusers DAOUser;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        DAOUser = new DAOusers();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        RequestObject ro = new RequestObject();
        ro.checkCookie(req.getCookies(), DAOUser);

        if (!ro.ok || ro.user.getUserRole() == 2) {
            resp.setStatus(406);
            resp.getWriter().write(ro.getResp());
            return;
        }

        Users user = DAOUser.getUserAndLotByIdAndToken(ro.user.getId(), ro.user.getToken());
        if (user == null) {
            resp.setStatus(406);
            resp.getWriter().write("what is wrong ?");
            return;
        }

        resp.setStatus(200);
        resp.getWriter().write(ParseJson.lotsToJson(user.getUserLots()));
    }
}
