package world.ucode.utils.auction;

import com.fasterxml.jackson.databind.ObjectMapper;
import world.ucode.model.db.dao.DAOlot;
import world.ucode.model.db.entetis.Lot;
import world.ucode.model.db.entetis.Users;
import world.ucode.utils.Utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AuctionUtils {

    public static boolean deleteAuction(HttpServletResponse resp, int lotId, DAOlot daoLot, Users user) throws IOException {
        Lot lot = daoLot.read(lotId);

        if (lot == null) {
            resp.setStatus(404);
            resp.getWriter().write("lot not found");
            return false;
        }

        if (lot.getSellerId() != user.getId()) {
            resp.setStatus(403);
            resp.getWriter().write("permission denied");
            return false;
        }

        daoLot.delete(lotId);
        return true;
    }

    public static boolean sendLot(HttpServletResponse resp, int lotId, DAOlot daoLot) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Lot lot = daoLot.read(lotId);

        if (lot == null) {
            resp.setStatus(404);
            resp.getWriter().write("lot not found");
            return false;
        }

        resp.setStatus(200);
        resp.getWriter().write(mapper.writeValueAsString(lot));
        return true;
    }

    public static boolean sendLots(HttpServletResponse resp,  DAOlot daoLot) throws IOException {
        List<Lot> listOfLot = daoLot.getAllLots();

        if (listOfLot == null) {
            resp.setStatus(404);
            resp.getWriter().write("lots not found");
            return false;
        }

        resp.setStatus(200);
        resp.getWriter().write(Utils.lotsToJsonArray(listOfLot).toJSONString());
        return true;
    }
}
