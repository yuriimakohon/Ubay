package world.ucode.API.feedback;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import world.ucode.model.db.dao.DAOfeedback;
import world.ucode.model.db.dao.DAOlot;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.model.db.entetis.Feedback;
import world.ucode.model.db.entetis.Lot;
import world.ucode.model.db.entetis.Users;
import world.ucode.utils.RequestObject;
import world.ucode.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/feedback/*")
public class ApiFeedback extends HttpServlet {
    private DAOusers daoUser;
    private DAOfeedback daoFeedback;
    private DAOlot daoLot;

    @Override
    public void init() {
        daoUser = new DAOusers();
        daoFeedback = new DAOfeedback();
        daoLot = new DAOlot();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int lotId = Utils.getId(req);
        List<Feedback> listOfFeedbacks = daoFeedback.getAllFeedbackforlot(lotId);

        if (listOfFeedbacks == null || listOfFeedbacks.isEmpty()) {
            resp.setStatus(404);
            resp.getWriter().write("there is no feedbacks or lot");
        } else {
            ObjectMapper mapper = new ObjectMapper();
            JSONParser jp = new JSONParser();
            JSONObject jo;
            JSONArray ja = new JSONArray();

            for (Feedback feedback : listOfFeedbacks) {
                Users user = daoUser.read(feedback.getUserId());;
                try {
                    jo = (JSONObject) jp.parse(mapper.writeValueAsString(feedback));

                    jo.put("login", user.getLogin());
                    jo.put("avatar", user.getUserphoto());
                    ja.add(jo);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            resp.setContentType("application/json;charset=utf-8");
            resp.setStatus(200);
            resp.getWriter().write(ja.toJSONString());
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

        updateRate(daoLot, feedback);

        resp.getWriter().write(ro.user.getLogin());
    }

    /**
     * Calculate new avg rate for auction by feedback evaluation:
     * {@code (feedbacksNumber * currentRate + mark) / (feedbacksNumber + 1)}
     * @param daoLot lot to be updated
     * @param feedback feedback with rate
     */
    private void updateRate(DAOlot daoLot, Feedback feedback) {
        Lot lot = daoLot.read(feedback.getLotId());
        int fNumbs = lot.getFeedbackNumber();
        float rate = lot.getRate();
        float avg = (fNumbs * rate + feedback.getMark()) / (fNumbs + 1);

        lot.setFeedbackNumber(fNumbs + 1);
        lot.setRate(avg);
        daoLot.update(lot);
    }

}
