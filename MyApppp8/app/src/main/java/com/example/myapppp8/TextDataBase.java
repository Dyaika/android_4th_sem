package com.example.myapppp8;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TextEntity.class}, version = 4)
public abstract class TextDataBase extends RoomDatabase {
    private static final String dbName = "texts";
    private static TextDataBase db;

    public static synchronized TextDataBase getDatabase(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context, TextDataBase.class, dbName)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return db;
    }

    public abstract TextDao textDao();
}