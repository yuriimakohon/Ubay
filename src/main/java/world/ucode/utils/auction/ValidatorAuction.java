package world.ucode.utils.auction;

import org.json.simple.JSONObject;
import world.ucode.model.db.entetis.Lot;

import javax.servlet.http.HttpServletRequest;
import java.net.StandardSocketOptions;
import java.util.Date;

public class ValidatorAuction {
    public Lot lot;

    public boolean validatorAuction(JSONObject jsonAuction) {
        String desc = jsonAuction.get("desc").toString();
        String title = jsonAuction.get("title").toString();
        String startTimeS = jsonAuction.get("startTime").toString();
        String durationS = jsonAuction.get("duration").toString();
        String startPriceS = jsonAuction.get("startPrice").toString();
        String maxPriceS = jsonAuction.get("maxPrice").toString();

        if (desc == null
                || title == null
                || startPriceS == null
                || startTimeS == null
                || durationS == null
                || maxPriceS == null) {
            return false;
        }


        long date = new Date().getTime();
        long startTime, duration, startPrice, maxPrice;

        try  {
            startTime = Long.parseLong(startTimeS);
            duration = startTime + (Integer.parseInt(durationS)*60*60*24*1000);
            startPrice = Long.parseLong(startPriceS);
            maxPrice = Long.parseLong(maxPriceS);

            if (startTime < date + 300
                    || duration < 1
                    || startPrice <= 0
                    || startPrice >= maxPrice) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        lot = new Lot();
        lot.setTitle(title);
        lot.setDescription(desc);
        lot.setStartTime(startTime);
        lot.setDuration(duration);
        lot.setPrice(startPrice);
        lot.setMaxPrice(maxPrice);
        lot.setRate(5);
        lot.setStatus(1);
        return true;
    }
}
