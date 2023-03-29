package com.example.myapp4.pojo;


import android.media.Image;
import android.widget.ImageView;
import android.widget.TextView;

public class Item {
    private int img;
    private String text;

    public Item(int img, String text) {
        this.img = img;
        this.text = text;
    }

    public int getImg() {
        return img;
    }

    public String getText() {
        return text;
    }
}
