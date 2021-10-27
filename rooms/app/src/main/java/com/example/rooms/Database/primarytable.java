package com.example.rooms.Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class primarytable {
    @PrimaryKey(autoGenerate = true)
    private int pid;
    private String roomName;
    private String dateTime;

    public primarytable( String roomName, String dateTime) {
        this.pid = pid;
        this.roomName = roomName;
        this.dateTime = dateTime;
    }


    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

//    @NonNull
//    @Override
//    public String toString() {
//        return roomName + " : " + dateTime;
//    }
}
