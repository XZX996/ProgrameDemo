package com.example.demo.pojo;

public class UserInfo {
    public String username;
    public String password1;
    public int remember;

    public int getRemember() {
        return remember;
    }

    public void setRemember(int remember) {
        this.remember = remember;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", password1='" + password1 + '\'' +
                ", remember='" + remember + '\'' +
                '}';
    }
}
