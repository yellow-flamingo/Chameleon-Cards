package com.android.chameleoncards;

public class Card {
    private int id;
    private byte[] image;
    private String username;

    public Card(int id, String username, byte[] image) {
        this.image = image;
        this.id = id;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
