package service;

import model.User;

import java.util.List;

public interface UserService {

    void add(User user);

    List<User> getAllUser();

    User getUserById(int id);

    User identifyUser();

    User getUserByEmail(String email);

}
