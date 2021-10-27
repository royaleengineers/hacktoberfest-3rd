package com.example.rooms.Database;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class relationp2d {
    @Embedded
    primarytable ptable;

    @Relation(parentColumn = "pid",entityColumn = "pid",entity = deviceaTable.class)
    public List<deviceaTable> dtable;

    public primarytable getPtable() {
        return ptable;
    }

    public void setPtable(primarytable ptable) {
        this.ptable = ptable;
    }

    public List<deviceaTable> getDtable() {
        return dtable;
    }

    public void setDtable(List<deviceaTable> dtable) {
        this.dtable = dtable;
    }
}
