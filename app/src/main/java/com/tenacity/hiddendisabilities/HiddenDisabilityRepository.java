package com.tenacity.hiddendisabilities;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;


class HiddenDisabilityRepository {
    private final HiddenDisabilityDao hiddendisabilitiesDao;
    private final LiveData <List <HiddenDisability>> allHiddenDisabilities;

    public HiddenDisabilityRepository(Application application) {
        HiddenDisabilityDatabase database = HiddenDisabilityDatabase.getInstance ( application );
        hiddendisabilitiesDao = database.hiddenDisabilityDao ();
        allHiddenDisabilities = hiddendisabilitiesDao.getAllHiddendisabilities ();
    }

    public void insert(HiddenDisability hiddenDisability) {
        new InsertHiddenDisabilitiesAsyncTask ( hiddendisabilitiesDao ).execute ( hiddenDisability );

    }

    public void update(HiddenDisability hiddenDisability) {
        new UpdateHiddenDisabilitiesAsyncTask ( hiddendisabilitiesDao ).execute ( hiddenDisability );
    }

    public void delete(HiddenDisability hiddenDisability) {
        new UpdateHiddenDisabilitiesAsyncTask.DeleteHiddenDisabilitiesAsyncTask ( hiddendisabilitiesDao ).execute ( hiddenDisability );
    }

    public void deleteAllHiddenDisabilities() {
        new UpdateHiddenDisabilitiesAsyncTask.DeleteALLHiddenDisabilitiesAsyncTask ( hiddendisabilitiesDao ).execute ();
    }

    public LiveData <List <HiddenDisability>> getAllHiddenDisabilities() {
        return allHiddenDisabilities;
    }

    private static class InsertHiddenDisabilitiesAsyncTask extends AsyncTask <HiddenDisability, Void, Void> {
        private final HiddenDisabilityDao hiddenDisabilityDao;

        private InsertHiddenDisabilitiesAsyncTask(HiddenDisabilityDao hiddenDisabilityDao) {
            this.hiddenDisabilityDao = hiddenDisabilityDao;
        }

        @Override
        protected Void doInBackground(HiddenDisability... hiddenDisabilities) {
            hiddenDisabilityDao.insert ( hiddenDisabilities[0] );
            return null;
        }

    }

    private static class UpdateHiddenDisabilitiesAsyncTask extends AsyncTask <HiddenDisability, Void, Void> {
        private final HiddenDisabilityDao hiddenDisabilityDao;

        private UpdateHiddenDisabilitiesAsyncTask(HiddenDisabilityDao hiddenDisabilityDao) {
            this.hiddenDisabilityDao = hiddenDisabilityDao;
        }

        @Override
        protected Void doInBackground(HiddenDisability... hiddenDisabilities) {
            hiddenDisabilityDao.update ( hiddenDisabilities[0] );
            return null;
        }

        private static class DeleteHiddenDisabilitiesAsyncTask extends AsyncTask <HiddenDisability, Void, Void> {
            private final HiddenDisabilityDao hiddenDisabilityDao;

            private DeleteHiddenDisabilitiesAsyncTask(HiddenDisabilityDao hiddenDisabilityDao) {
                this.hiddenDisabilityDao = hiddenDisabilityDao;
            }

            @Override
            protected Void doInBackground(HiddenDisability... hiddenDisabilities) {
                hiddenDisabilityDao.delete ( hiddenDisabilities[0] );
                return null;
            }

        }

        private static class DeleteALLHiddenDisabilitiesAsyncTask extends AsyncTask <Void, Void, Void> {
            private final HiddenDisabilityDao hiddenDisabilityDao;

            private DeleteALLHiddenDisabilitiesAsyncTask(HiddenDisabilityDao hiddenDisabilityDao) {
                this.hiddenDisabilityDao = hiddenDisabilityDao;
            }

            @Override
            protected Void doInBackground(Void... voids) {
                hiddenDisabilityDao.deleteAllHiddendisabilities ();
                return null;
            }

        }

    }


}