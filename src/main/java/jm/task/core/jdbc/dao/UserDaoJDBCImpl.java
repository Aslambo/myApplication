package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static String INSERT = "INSERT INTO users VALUES(id, ?,?,?)";
    private static String GET_ALL = "SELECT * FROM users";
    private static String DELETE = "DELETE FROM users WHERE id=?";
    private static PreparedStatement preparedStatement;
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException{
            preparedStatement = Util.getConnection().prepareStatement(
                    "create table IF NOT EXISTS users" +
                            "(" +
                            "    id       int auto_increment," +
                            "    name     varchar(40)," +
                            "    lastname varchar(40)," +
                            "    age      int        ," +
                            "        primary key (id)" +
                            ");");
            preparedStatement.execute();
    }

    public void dropUsersTable() throws SQLException {
        preparedStatement = Util.getConnection().prepareStatement("DROP TABLE IF EXISTS users");
        preparedStatement.execute();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        preparedStatement = Util.getConnection().prepareStatement(INSERT);
        preparedStatement.setString(1,name);
        preparedStatement.setString(2, lastName);
        preparedStatement.setByte(3,age);
        preparedStatement.execute();
        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) throws SQLException {
        preparedStatement = Util.getConnection().prepareStatement(DELETE);
        preparedStatement.setLong(1,id);
        preparedStatement.execute();

    }

    public List<User> getAllUsers() throws SQLException {
        List<User> list = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery(GET_ALL);
        while (resultSet.next()){
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setLastName(resultSet.getString("lastname"));
            user.setAge(resultSet.getByte("age"));
            list.add(user);
        }
        return list;
    }

    public void cleanUsersTable() throws SQLException {
        preparedStatement.execute("TRUNCATE TABLE users");
    }
}
