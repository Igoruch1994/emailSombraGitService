package model;

import lombok.Data;

@Data
public class User {

    private int id;

    private String login;

    private String role = "user";

    private String password;

    private String email;

    private boolean enabled = false;

    public User(){}

    public User(int id, String login, String role, String password, String email, boolean enabled) {
        this.id = id;
        this.login = login;
        this.role = role;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
    }


}
