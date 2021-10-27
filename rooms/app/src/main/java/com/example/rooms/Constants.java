package com.example.rooms;

class Constants {

    // values have to be globally unique

//    static final String INTENT_ACTION_DISCONNECT = BuildConfig.APPLICATION_ID + ".Disconnect";
//    static final String NOTIFICATION_CHANNEL = BuildConfig.APPLICATION_ID + ".Channel";
//    static final String INTENT_CLASS_MAIN_ACTIVITY = BuildConfig.APPLICATION_ID + ".MainActivity";

    static final String INTENT_ACTION_DISCONNECT = "com.example.rooms.Disconnect";
    static final String INTENT_CLASS_MAIN_ACTIVITY = "com.example.rooms.MainActivity";
    static final String NOTIFICATION_CHANNEL = "com.example.rooms.Channel";

    // values have to be unique within each app
    static final int NOTIFY_MANAGER_START_FOREGROUND_SERVICE = 1001;

    private Constants() {}
}
