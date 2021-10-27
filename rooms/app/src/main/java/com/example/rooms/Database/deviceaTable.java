package com.example.rooms.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class deviceaTable {

    @PrimaryKey(autoGenerate = true)
    private int deviceid;
    private int pid;
    private String deviceName;
    private String logoAsset;
    private String toggleON;
    private String toggleOFF;
//    private String slider;

    public deviceaTable( int pid, String deviceName, String logoAsset,String toggleON, String toggleOFF ) {
        this.deviceid = deviceid;
        this.pid = pid;
        this.deviceName = deviceName;
        this.logoAsset=logoAsset;
        this.toggleON = toggleON;
        this.toggleOFF=toggleOFF;
//        this.slider = slider;

    }

    public String getLogoAsset() {
        return logoAsset;
    }

    public void setLogoAsset(String logoAsset) {
        this.logoAsset = logoAsset;
    }

    public int getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(int deviceid) {
        this.deviceid = deviceid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getToggleON() {
        return toggleON;
    }

    public void setToggleON(String toggleON) {
        this.toggleON = toggleON;
    }

    public String getToggleOFF() {
        return toggleOFF;
    }

    public void setToggleOFF(String toggleOFF) {
        this.toggleOFF = toggleOFF;
    }

//    public String getSlider() {
//        return slider;
//    }
//
//    public void setSlider(String slider) {
//        this.slider = slider;
//    }
}
