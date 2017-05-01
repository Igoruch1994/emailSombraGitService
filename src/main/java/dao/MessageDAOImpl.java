package dao;

import jdbc.ConnectionJDBC;
import model.Message;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import service.UserService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAOImpl implements MessageDAO {

    @Autowired
    UserService userService;

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement = null;

    final String getAllMessagesQuery = "SELECT * FROM message where receiver_id = ?;";
    final String addNewMessageQuery = "insert into message(text,receiver_id,sender_id) value (?,?,?)";

    public MessageDAOImpl() throws ClassNotFoundException, SQLException {
        connection = ConnectionJDBC.getMySQLConnection();
        statement = connection.createStatement();
    }

    @Override
    public List<Message> getAllMessageForCurrentUser(int id) {
        List<Message> messageList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(getAllMessagesQuery);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int iD = resultSet.getInt("id");
                String  text = resultSet.getString("text");
                int sender_id = resultSet.getInt("sender_id");
                int receiver_id = resultSet.getInt("receiver_id");
                User sender = userService.getUserById(sender_id);
                Message message = new Message(iD,text,receiver_id,sender);
                messageList.add(message);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return messageList;
    }

    @Override
    public void add(Message message) {
        try {
            preparedStatement = connection.prepareStatement(addNewMessageQuery);
            preparedStatement.setString(1,message.getText());
            preparedStatement.setInt(2,message.getReceiver_id());
            preparedStatement.setInt(3,message.getSender().getId());
            preparedStatement.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
