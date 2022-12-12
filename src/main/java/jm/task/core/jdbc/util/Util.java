package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import java.util.Properties;

public class Util {

    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory(){
        try {
            Properties properties = new Properties();
            properties.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/mybd");
            properties.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            properties.setProperty(Environment.USER, "root");
            properties.setProperty(Environment.PASS, "root");
            properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
            properties.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            properties.setProperty(Environment.SHOW_SQL, "true");

            sessionFactory = new Configuration().addAnnotatedClass(User.class)
                    .addProperties(properties).buildSessionFactory();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

//    public static Connection getConnection() {
//        Connection connection = null;
//        try {
//            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//            connection = DriverManager.getConnection(URL, USER, PASSWORD);
//            connection.setAutoCommit(false);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }
}