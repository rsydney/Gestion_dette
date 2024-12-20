package com.ism.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private int id; 
    private Client client;
    private String surname;     
    private String login;
    private String password;
    private String role;      
    private boolean isActive = true;

    public User(int id, String surname, String login, String password, String role) {
        this.id = id; 
        this.login = login;
        this.password = password;
        this.role = role;
    }
    public User(String email, String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }
}
