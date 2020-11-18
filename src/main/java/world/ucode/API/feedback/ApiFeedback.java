package world.ucode.API.feedback;

import org.json.simple.JSONArray;
import world.ucode.model.db.dao.DAOfeedback;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.model.db.entetis.Feedback;
import world.ucode.utils.RequestObject;
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
    }

}
