package world.ucode.model.db.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getCurrentSessionFromConfig() {
        if (sessionFactory == null) {
            try {
                Configuration config = new Configuration();
                config.configure();
                // local SessionFactory bean created
                SessionFactory sessionFactory = config.buildSessionFactory();
                System.out.println("Hibernate Java Config serviceRegistry created");
                return sessionFactory;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
