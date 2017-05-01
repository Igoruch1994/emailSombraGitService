package dao;


import jdbc.ConnectionJDBC;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement = null;
    final String saveUserQuery = "insert into user(email,login,enabled,password,role) value(?,?,?,?,?);";
    final String getAllUserQuery = "SELECT * FROM user;";
    final String getUserByIdQuery = "Select * from user where id = ?";
    final String getUserByLoginQuery = "Select * from user where login = ?";
    final String getUserByEmailQuery = "Select * from user where email = ?";

    public UserDAOImpl() throws ClassNotFoundException, SQLException {
        connection = ConnectionJDBC.getMySQLConnection();
        statement = connection.createStatement();

    }

    @Override
    public void add(User user) {
        try {
            preparedStatement = connection.prepareStatement(saveUserQuery);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setBoolean(3, user.isEnabled());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getRole());
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUser() {
        List<User> userList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(getAllUserQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                Boolean enabled = resultSet.getBoolean("enabled");
                User user = new User(id, login, role, password, email, enabled);
                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User getUserById(int id) {
        User user = null;
        try {
            preparedStatement = connection.prepareStatement(getUserByIdQuery);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            user = initUserByResultSet(resultSet);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByLogin(String login) {
        User user = null;
        try{
            preparedStatement = connection.prepareStatement(getUserByLoginQuery);
            preparedStatement.setString(1,login);
            ResultSet resultSet = preparedStatement.executeQuery();
            user = initUserByResultSet(resultSet);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) {

        User u = null;
        try{
            preparedStatement = connection.prepareStatement(getUserByEmailQuery);
            preparedStatement.setString(1,email);
            ResultSet rs = preparedStatement.executeQuery();
            u = initUserByResultSet(rs);
        }
        catch (Exception ex){
             ex.printStackTrace();
        }
        return u;
    }

    private User initUserByResultSet(ResultSet resultSet){
        User user = null;
        try {
            while (resultSet.next()) {
                int iD = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                Boolean enabled = resultSet.getBoolean("enabled");
                user = new User(iD, login, role, password, email, enabled);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }


}
