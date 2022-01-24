package com.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
//@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String name;
    private String password;
    private String role;

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
