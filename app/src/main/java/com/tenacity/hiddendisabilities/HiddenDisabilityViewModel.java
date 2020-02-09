package com.tenacity.hiddendisabilities;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

class HiddenDisabilityViewModel extends AndroidViewModel {
    private final HiddenDisabilityRepository repository;
    private final LiveData <List <HiddenDisability>> allHiddenDisabilities;

    public HiddenDisabilityViewModel(@NonNull Application application) {
        super ( application );
        repository = new HiddenDisabilityRepository ( application );
        allHiddenDisabilities = repository.getAllHiddenDisabilities ();

    }

    public void insert(HiddenDisability hiddenDisability) {
        repository.insert ( hiddenDisability );
    }

    public void update(HiddenDisability hiddenDisability) {
        repository.update ( hiddenDisability );
    }

    public void delete(HiddenDisability hiddenDisability) {
        repository.delete ( hiddenDisability );
    }

    public void deleteAllHiddenDisabilities() {
        repository.deleteAllHiddenDisabilities ();
    }

    public LiveData <List <HiddenDisability>> getAllHiddenDisabilities() {
        return allHiddenDisabilities;
    }


}
