package com.example.demo.model;

public class UserDTO {

    String user_id;
    String user_passwd;
    String user_role;

    public String getUser_id() {
        return user_id;
    }

    public String getUser_passwd() {
        return user_passwd;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setUser_passwd(String user_passwd) {
        this.user_passwd = user_passwd;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }
}
