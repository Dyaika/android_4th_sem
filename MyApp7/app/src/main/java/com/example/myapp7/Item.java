package com.example.myapp7;

public class Item {
    private String name;
    private String descryption;
    private int image;

    public String getName() {
        return name;
    }

    public String getDescryption() {
        return descryption;
    }

    public int getImage() {
        return image;
    }

    public Item(String name, String descryption, int image) {
        this.name = name;
        this.descryption = descryption;
        this.image = image;
    }
}
