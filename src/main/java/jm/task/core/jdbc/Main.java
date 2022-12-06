package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static jm.task.core.jdbc.util.Util.getConnection;

public class Main {
    public static void main(String[] args) throws SQLException {
        Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();

        userDao.saveUser("Johnny", "Depp", (byte) 20);
        userDao.saveUser("Mike", "Tyson", (byte) 25);
        userDao.saveUser("Tom", "Cruise", (byte) 31);
        userDao.saveUser("Tobey", "Maguire", (byte) 38);

        userDao.removeUserById(1);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
