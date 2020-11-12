package world.ucode.API.user.put;


import world.ucode.model.db.dao.DAOusers;
import world.ucode.utils.RequestObject;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/change_balance")
public class ChangeBalance extends HttpServlet {
    DAOusers daoUser;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        daoUser = new DAOusers();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        RequestObject ro = new RequestObject();

        ro.checkJson(req);
        ro.checkCookie(req.getCookies(), daoUser);

        System.out.println(ro.jo.toJSONString());

        if (ro.ok) {
            long balance;

            try {
                balance = Long.parseLong(ro.jo.get("balance").toString());
            } catch (NumberFormatException e) {
                resp.setStatus(409);
                resp.getWriter().write("validation fail");
                return;
            }

            if (ro.user.getBalance() + balance < 0) {
                resp.setStatus(409);
                resp.getWriter().write("small many");
            } else {
                ro.user.setBalance(ro.user.getBalance() + balance);
                daoUser.update(ro.user);
                resp.setStatus(ro.getStatus());
            }
        } else {
            resp.setStatus(409);
            resp.getWriter().write("validation fail");
        }
    }
}
