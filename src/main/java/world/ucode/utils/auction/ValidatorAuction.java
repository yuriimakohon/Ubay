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
        long startTime, duration, startPrice, maxPrice;

        try  {
            startTime = Long.parseLong(startTimeS.toString());
            duration = startTime + (Integer.parseInt(durationS.toString())*60*60*24*1000);
            startPrice = Long.parseLong(startPriceS.toString());
            maxPrice = Long.parseLong(maxPriceS.toString());

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
        lot.setBidId(0);
        lot.setRate(5);
        lot.setStatus(1);
        return true;
    }
}
