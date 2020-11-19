package world.ucode.API.feedback;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import world.ucode.model.db.dao.DAOfeedback;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.model.db.entetis.Feedback;
import world.ucode.model.db.entetis.Lot;
import world.ucode.utils.ParseJson;
import world.ucode.utils.RequestObject;
import world.ucode.utils.Utils;
import world.ucode.utils.auction.ValidatorAuction;

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

@WebServlet("/api/feedback/*")
public class ApiFeedback extends HttpServlet {
    private DAOusers daoUser;
    private DAOfeedback daoFeedback;

    @Override
    public void init() throws ServletException {
        daoUser = new DAOusers();
        daoFeedback = new DAOfeedback();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int lotId = Utils.getId(req);
        List<Feedback> listOfFeedbacks = daoFeedback.getAllFeedbackforlot(lotId);

        if (listOfFeedbacks.isEmpty()) {
            resp.setStatus(404);
            resp.getWriter().write("there is no feedbacks or lot");
        } else {
            ObjectMapper mapper = new ObjectMapper();
            JSONObject jo = new JSONObject();
            JSONArray ja = new JSONArray();

            for (Feedback feedback : listOfFeedbacks)
                ja.add(mapper.writeValueAsString(feedback));

            jo.put("feedbacks", ja);
            resp.setContentType("application/json;charset=utf-8");
            resp.setStatus(200);
            resp.getWriter().write(jo.toJSONString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        RequestObject ro = new RequestObject();

        ro.checkCookie(req.getCookies(), daoUser);
        ro.checkJson(req);

        if (!ro.ok) {
            resp.setStatus(403);
            resp.getWriter().write("permission denied");
            return;
        }

        Feedback feedback = new Feedback(
                ro.jo.get("text").toString(),
                Integer.parseInt(ro.jo.get("evaluation").toString()),
                Integer.parseInt(ro.jo.get("lotId").toString()),
                ro.user.getId()
                );

        daoFeedback.create(feedback);

        resp.getWriter().write(ro.user.getLogin());
    }

}
