package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private static Configuration configuration;

    private static SessionFactory sessionFactory;

    static {
        configuration = new Configuration();
        configuration.configure();

        sessionFactory = configuration.buildSessionFactory();
    }


    public static Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
