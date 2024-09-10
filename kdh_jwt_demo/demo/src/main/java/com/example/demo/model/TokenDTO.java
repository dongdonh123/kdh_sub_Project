package com.example.demo.model;

public class TokenDTO {

    private Long id;
    private String user_name;
    private String refresh;
    private String expiration;

    public Long getId() {
        return id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getRefresh() {
        return refresh;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setRefresh(String refresh) {
        this.refresh = refresh;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }
}
