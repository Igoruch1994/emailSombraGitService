package dao;

import model.Message;
import java.util.List;

public interface MessageDAO {

    List<Message> getAllMessageForCurrentUser(int id);

    void add(Message message);

}
