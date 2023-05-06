package com.example.rickandmorty.present.locationdetails;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.api.CharactersResult;
import com.example.rickandmorty.api.LocationsResult;
import com.example.rickandmorty.data.remove.service.RickAndMortyApi;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kotlin.text.StringsKt;

public class LocationDetailsViewModel extends ViewModel {
     private final RickAndMortyApi api;
     @Inject
    LocationDetailsViewModel(RickAndMortyApi api){
         this.api = api;
     }
    private MutableLiveData<LocationsResult> currentLocation;

    public LiveData<LocationsResult> getCurrentLocation(int id) {
        if (currentLocation == null) {
            currentLocation = new MutableLiveData<LocationsResult>();
            loadCurrentLocation(id);
        }
        return currentLocation;
    }

    @SuppressLint("CheckResult")
    private void loadCurrentLocation(int id) {
        api.getLocationForDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> currentLocation.setValue(response.body()), Throwable::printStackTrace);
    }


    private MutableLiveData<ArrayList<CharactersResult>> charactersFromCurrentLocation;

    public LiveData<ArrayList<CharactersResult>> getCharactersFromCurrentEpisode() {
        if (charactersFromCurrentLocation == null) {
            charactersFromCurrentLocation = new MutableLiveData<ArrayList<CharactersResult>>();
            loadCharactersFromLocation();
        }
        return charactersFromCurrentLocation;
    }

    @SuppressLint("CheckResult")
    private void loadCharactersFromLocation() {
        api.getMultiCharactersForLocationDetails(charactersUrlIds.getValue())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> charactersFromCurrentLocation.setValue(response.body()), Throwable::printStackTrace);


    }

    private MutableLiveData<ArrayList<Integer>> charactersUrlIds = new MutableLiveData<>();

    public LiveData<ArrayList<Integer>> getCharactersIds(int id) {
        getIdsCharacters(id);
        return charactersUrlIds;
    }

    @SuppressLint("CheckResult")
    private void getIdsCharacters(int id) {
        api.getLocationForDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    charactersUrlIds.setValue(getIdssss(response.body().getResidents()));
                    loadCharactersFromLocation();
                }, Throwable::printStackTrace);
    }

    private ArrayList<Integer> getIdssss(List<String> list) {
        ArrayList<Integer> integerArrayList = new ArrayList<Integer>();
        int var2 = 0;
        int var3 = list.size();

        while (var2 < var3) {
            int i = var2++;
            switch (list.get(i).length()) {
                case 43:
                    integerArrayList.add(Integer.parseInt(StringsKt.takeLast((String) list.get(i), 1)));
                    break;
                case 44:
                    integerArrayList.add(Integer.parseInt(StringsKt.takeLast((String) list.get(i), 2)));
                    break;
                case 45:
                    integerArrayList.add(Integer.parseInt(StringsKt.takeLast((String) list.get(i), 3)));

            }
        }
        return integerArrayList;
    }


}
