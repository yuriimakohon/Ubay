package world.ucode.model.db.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory mysessionFactory;

    public static SessionFactory getSessionFactory() {
        if (mysessionFactory == null) {
            try {
                Configuration config = new Configuration();
                config.configure();
                // local SessionFactory bean created
                mysessionFactory = config.buildSessionFactory();
                System.out.println("*************************Hibernate Java Config service Registry created************************");
                return mysessionFactory;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mysessionFactory;
    }
}
