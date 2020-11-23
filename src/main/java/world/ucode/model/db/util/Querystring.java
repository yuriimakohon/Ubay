package world.ucode.model.db.util;


import org.hibernate.Session;
import org.hibernate.query.Query;
import world.ucode.model.db.entetis.Lot;

import java.util.List;

public class Querystring {
    public static  Query stringmaker(List<String> categories, Session session, String tittle, double startPrice, int rate, List<Integer> status, String login, int userid) {
        Query query = null;
        StringBuilder qua = new StringBuilder("Select l from Lot l");
        Boolean categor = false;
        Boolean tit = false;
        Boolean startpr = false;
        Boolean rat = false;
        Boolean stat = false;

        Boolean log = false;
        Boolean uid = false;

        if (login != null) {
            qua.append(" inner join Users u ON l.sellerId = u.id where u.login=:login");
            log = true;
        }
        else if (userid != 0) {
            qua.append(" inner join Users u ON l.sellerId = u.id where u.id=:userid");
            uid = true;
        }

        if (status != null && status.size() > 0) {
            if (uid || log)
                qua.append(" and (status=");
            else
                qua.append(" where (status=");
            for (int i = 0; i < status.size(); i++) {
                qua.append(status.get(i));
                if (i != status.size() - 1)
                    qua.append(" or status=");
            }
            qua.append(")");
            stat = true;
        }

        if (categories != null && categories.size() > 0) {
             if (uid || log || stat)
                 qua.append(" and category like ");
             else
                 qua.append(" where category like ");
             for (int i = 0; i < categories.size(); i++) {
                 qua.append(":" + categories.get(i));
                 if (i != categories.size() - 1)
                     qua.append(" and category like ");
             }
             categor = true;
         }
        if (tittle != null) {
            if (uid || log || categor || stat)
                qua.append(" and title like :tittle");
            else
                qua.append(" where title like :tittle");
            tit = true;
        }
        if (startPrice > 0) {
            if (uid || log || categor || tit || stat)
                qua.append(" and price<=:price");
            else
                qua.append(" where price<=:price");
            startpr = true;
        }
        if (rate > 0) {
            if (uid || log || categor || tit || startpr || stat)
                qua.append(" and rate>=:rate");
            else
                qua.append(" where rate>=:rate");
            rat = true;
        }

//        System.out.println(qua);
        query = session.createQuery(qua.toString(), Lot.class);
        if (categor) {
            for (String category : categories) {
                query.setParameter(category, "%" + category + "%");
            }
        }
        if (tit)
            query.setParameter("tittle",  tittle + "%");
        if (startpr)
            query.setParameter("price", startPrice);
        if (rat)
            query.setParameter("rate", (float)rate);
        if (log)
            query.setParameter("login", login);
        if (uid)
            query.setParameter("userid", userid);

        return query;
    }


}
