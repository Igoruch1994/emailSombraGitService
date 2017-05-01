package model;

import lombok.Data;

@Data
public class Message {

    private int id;

    private String text;

    int receiver_id;

    private User sender;

    public Message(){}

    public Message(int id, String text, int receiver_id, User sender) {
        this.id = id;
        this.text = text;
        this.receiver_id = receiver_id;
        this.sender = sender;
    }
}
