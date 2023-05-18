package com.example.myapppp8;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "texts")
public class TextEntity {
    @NotNull
    @PrimaryKey(autoGenerate = true)
    Integer id;

    @ColumnInfo(name = "key")
    String key;

    @ColumnInfo(name = "text")
    String text;

    public void setId(@NotNull Integer id) {
        this.id = id;
    }

    @NotNull
    public Integer getId() {
        return id;
    }

    public TextEntity(String key, String text) {
        this.text = text;
        this.key = key;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}