package world.ucode.utils.auction;

import org.json.simple.JSONObject;
import world.ucode.model.db.entetis.Lot;

import java.util.Date;

public class ValidatorAuction {
    public Lot lot;

    public boolean validatorAuction(JSONObject jsonAuction) {
        Object desc = jsonAuction.get("desc");
        Object title = jsonAuction.get("title");
        Object startTimeS = jsonAuction.get("startTime");
        Object durationS = jsonAuction.get("duration");
        Object startPriceS = jsonAuction.get("startPrice");
        Object maxPriceS = jsonAuction.get("maxPrice");
        Object categories = jsonAuction.get("categories");

        if (desc == null
                || title == null
                || startPriceS == null
                || startTimeS == null
                || durationS == null
                || maxPriceS == null
                || categories == null) {
            return false;
        }


        long date = new Date().getTime();
        long startTime, duration;
        double startPrice, maxPrice;

        try  {
//            startTime = Long.parseLong(startTimeS.toString());
            startTime = new Date().getTime() + 5_000;
            duration = startTime + (Integer.parseInt(durationS.toString())*60*60*24*1000);
//            duration = startTime + 60_000;      // TODO: change to ^^^^^^^^^^^^^^^^^^^^^
            startPrice = Double.parseDouble(startPriceS.toString());
            maxPrice = Double.parseDouble(maxPriceS.toString());

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
        lot.setTitle(title.toString());
        lot.setDescription(desc.toString());
        lot.setStartTime(startTime);
        lot.setDuration(duration);
        lot.setPrice(startPrice);
        lot.setMaxPrice(maxPrice);
        lot.setCategory(categories.toString());
        lot.setFeedbackNumber(0);
        lot.setBidId(0);
        lot.setRate(5);
        lot.setStatus(1);
        return true;
    }
}
