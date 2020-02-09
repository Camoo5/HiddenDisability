package com.tenacity.hiddendisabilities;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {HiddenDisability.class}, version = 1)
@TypeConverters({HiddenDisability.DateTypeConverter.class})
public abstract class HiddenDisabilityDatabase extends RoomDatabase {

    private static HiddenDisabilityDatabase instance;

    public abstract HiddenDisabilityDao hiddenDisabilityDao ();

    public static synchronized HiddenDisabilityDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder ( context.getApplicationContext (),
                    HiddenDisabilityDatabase.class, "hiddendisability_database" )
                    .fallbackToDestructiveMigration ()
                    .addCallback ( roomCallback )
                    .build ();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback () {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate ( db );
            new PopulateDbAsyncTask ( instance ).execute ();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask <Void, Void, Void> {
        private final HiddenDisabilityDao hiddenDisabilityDao;

        private PopulateDbAsyncTask(HiddenDisabilityDatabase db) {
            hiddenDisabilityDao = db.hiddenDisabilityDao ();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            hiddenDisabilityDao.insert ( new HiddenDisability ( "disability 1", "specialist 1", "treatment 1", "note_title 1", "note_description 1", 1 ) );
            hiddenDisabilityDao.insert ( new HiddenDisability ( "disability 2", "specialist 2", "treatment 2", "note_title 2", "note_description 2", 2 ) );
            hiddenDisabilityDao.insert ( new HiddenDisability ( "disability 3", "specialist 3", "treatment 3", "note_title 3", "note_description 3", 3 ) );
            return null;
        }
    }

}
