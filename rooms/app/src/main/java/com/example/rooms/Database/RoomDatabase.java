package com.example.rooms.Database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import com.example.rooms.DAO;

@Database(entities={primarytable.class,deviceaTable.class}, version = 2, exportSchema = false)
public abstract class RoomDatabase extends androidx.room.RoomDatabase {
    private static RoomDatabase roomDatabase;

    public static synchronized RoomDatabase getDatabase(Context context){
        if(roomDatabase==null){
            roomDatabase=Room.databaseBuilder(context,RoomDatabase.class,"room_db")
                    .allowMainThreadQueries().build();
        }
        return roomDatabase;
    }

    public abstract DAO roomDao();
}
