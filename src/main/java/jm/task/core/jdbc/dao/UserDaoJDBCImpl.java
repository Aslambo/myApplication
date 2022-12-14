//package jm.task.core.jdbc.dao;
//
//import jm.task.core.jdbc.model.User;
//import jm.task.core.jdbc.util.Util;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserDaoJDBCImpl implements UserDao {
//    private static String INSERT = "INSERT INTO users VALUES(id, ?,?,?)";
//    private static String GET_ALL = "SELECT * FROM users";
//    private static String DELETE = "DELETE FROM users WHERE id=?";
//    private static Connection connection;
//
//    public UserDaoJDBCImpl() {
//
//    }
//
//    public void createUsersTable() throws SQLException {
//        connection = Util.getConnection();
//        try (Statement statement = connection.createStatement()) {
//            statement.execute(
//                    "create table IF NOT EXISTS users" +
//                            "(" +
//                            "    id       int auto_increment," +
//                            "    name     varchar(40)," +
//                            "    lastname varchar(40)," +
//                            "    age      int        ," +
//                            "        primary key (id)" +
//                            ");");
//            connection.commit();
//            connection.close();
//        } catch (SQLException se) {
//            se.printStackTrace();
//            connection.rollback();
//        }
//    }
//
//    public void dropUsersTable() throws SQLException {
//        connection = Util.getConnection();
//        try (Statement statement = connection.createStatement()) {
//            statement.execute("DROP TABLE IF EXISTS users");
//            connection.commit();
//            connection.close();
//        } catch (SQLException se) {
//            se.printStackTrace();
//            connection.rollback();
//        }
//    }
//
//    public void saveUser(String name, String lastName, byte age) throws SQLException {
//        connection = Util.getConnection();
//        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
//            preparedStatement.setString(1, name);
//            preparedStatement.setString(2, lastName);
//            preparedStatement.setByte(3, age);
//            preparedStatement.execute();
//            connection.commit();
//            connection.close();
//            System.out.println("User ?? ???????????? ??? " + name + " ???????????????? ?? ???????? ????????????");
//        } catch (SQLException se) {
//            se.printStackTrace();
//            connection.rollback();
//        }
//    }
//
//    public void removeUserById(long id) throws SQLException {
//        connection = Util.getConnection();
//        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
//            preparedStatement.setLong(1, id);
//            preparedStatement.execute();
//            connection.commit();
//            connection.close();
//        } catch (SQLException se) {
//            se.printStackTrace();
//            connection.rollback();
//        }
//    }
//
//    public List<User> getAllUsers() throws SQLException {
//        connection = Util.getConnection();
//        List<User> list = new ArrayList<>();
//        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL);
//             ResultSet resultSet = preparedStatement.executeQuery(GET_ALL)) {
//            while (resultSet.next()) {
//                User user = new User();
//                user.setId(resultSet.getLong("id"));
//                user.setName(resultSet.getString("name"));
//                user.setLastName(resultSet.getString("lastname"));
//                user.setAge(resultSet.getByte("age"));
//                list.add(user);
//            }
//            connection.commit();
//            connection.close();
//        } catch (SQLException se) {
//            se.printStackTrace();
//            connection.rollback();
//        }
//        return list;
//    }
//
//    public void cleanUsersTable() throws SQLException {
//        connection = Util.getConnection();
//        try (Statement statement = connection.createStatement()) {
//            statement.execute("TRUNCATE TABLE users");
//            connection.commit();
//            connection.close();
//        } catch (SQLException se) {
//            se.printStackTrace();
//            connection.rollback();
//        }
//    }
//}

