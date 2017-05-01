package dao;

import model.User;
import java.util.List;

public interface UserDAO {

    void add(User user);

    List<User> getAllUser();

    User getUserById(int id);

    User getUserByLogin(String login);

    User getUserByEmail(String email);

}
