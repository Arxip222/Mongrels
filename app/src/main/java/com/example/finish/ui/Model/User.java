package com.example.finish.ui.Model;

public class User {

    public String uid;
    public String email;
    public String image;
    public String name;
    public String phone;
    public String password;
    public String onlineStatus;
    public String typingTo;

    public User() {
    }

    public User(String uid, String email, String image, String name, String phone, String password) {
        this.uid = uid;
        this.email = email;
        this.image = image;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.onlineStatus = onlineStatus;
        this.typingTo = typingTo;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getTypingTo() {
        return typingTo;
    }

    public void setTypingTo(String typingTo) {
        this.typingTo = typingTo;
    }
}