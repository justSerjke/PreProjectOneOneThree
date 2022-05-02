package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Util {
    private static SessionFactory sessionFactory;
    
    public static SessionFactory getConnection() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration()
                        .setProperty("hibernate.connection.url", "jdbc:mysql://localhost/mydb")
                        .setProperty("hibernate.connection.username", "root")
                        .setProperty("hibernate.connection.password", "root")
                        .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                        .addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}