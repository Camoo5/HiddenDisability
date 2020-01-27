package com.tenacity.hiddendisabilities;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database (entities = {HiddenDisabilities.class}, version = 1)
public abstract class HiddenDisabilitiesDatabase extends RoomDatabase {
    private static HiddenDisabilitiesDatabase instance;

    public abstract HiddenDisabilitiesDao hiddenDisabilitiesDao();

    public static synchronized HiddenDisabilitiesDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    HiddenDisabilitiesDatabase.class, "hiddendisabilities_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }


}
