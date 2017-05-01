package service;

import dao.MessageDAO;
import model.Message;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageDAO messageDAO;

    @Autowired
    UserService userService;

    @Override
    public List<Message> getAllMessageForCurrentUser() {
        return messageDAO.getAllMessageForCurrentUser(userService.identifyUser().getId());
    }

    @Override
    public void add(Message message,String email) {
        if (message!=null){
            User userSender = userService.identifyUser();
            User userReceiver = userService.getUserByEmail(email);
            if(userReceiver!=null) {
                message.setSender(userSender);
                message.setReceiver_id(userReceiver.getId());
                messageDAO.add(message);
            }
        }
    }
}
