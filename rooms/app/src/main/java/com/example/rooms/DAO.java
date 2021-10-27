package com.example.rooms;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.rooms.Database.deviceaTable;
import com.example.rooms.Database.primarytable;

import java.util.List;

@Dao
public interface DAO {

    @Insert(onConflict= OnConflictStrategy.IGNORE)
    public long roomInsertion(primarytable room);

//    @Query("SELECT * FROM primarytable ORDER BY pid DESC")
//    List getRoom();

    @Query("SELECT * FROM primarytable")
    List<primarytable> getAllRooms();

    @Query("UPDATE primarytable SET roomName = :roomN WHERE pid = :id")
    void updateRoom(String roomN, int id);

    @Query("DELETE FROM primarytable WHERE pid =:id")
    void deleteRoom(int id);

    @Insert(onConflict= OnConflictStrategy.IGNORE)
    public long switchInsertion(deviceaTable switches);

    @Query("SELECT * FROM deviceaTable ORDER BY pid ASC")
    List<deviceaTable> getAllSwitches();

    @Query("UPDATE deviceaTable SET deviceName = :deviceN WHERE deviceid = :did")
    void updateSwitch(String deviceN, int did);

    @Query("DELETE FROM deviceaTable WHERE deviceid =:did")
    void deleteSwitch(int did);


}
