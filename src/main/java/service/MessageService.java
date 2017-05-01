package service;

import model.Message;

import java.util.List;

public interface MessageService {

    List<Message> getAllMessageForCurrentUser();

    void add(Message message,String email);

}
