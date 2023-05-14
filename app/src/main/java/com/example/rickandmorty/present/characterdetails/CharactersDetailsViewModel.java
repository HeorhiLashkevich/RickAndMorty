package com.example.rickandmorty.present.characterdetails;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.domain.model.CharactersResult;
import com.example.rickandmorty.data.remove.service.model.EpisodesResult;
import com.example.rickandmorty.data.api.RickAndMortyApi;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kotlin.text.StringsKt;


public class CharactersDetailsViewModel extends ViewModel {
    private final RickAndMortyApi api;

    @Inject
    CharactersDetailsViewModel(RickAndMortyApi api) {
        this.api = api;
    }
        private MutableLiveData<CharactersResult> character;

    public LiveData<CharactersResult> getCharacterValue(int id) {
        if (character == null) {
            character = new MutableLiveData<CharactersResult>();
            loadCharacter(id);
        }
        return character;
    }


    @SuppressLint("CheckResult")
    private void loadCharacter(int id) {
//        NetworkController.INSTANCE.getRickAndMortyApi().getCharacter(id)
        api.getCharacter(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> character.setValue(response.body()), Throwable::printStackTrace
                )
        ;
    }

    private final MutableLiveData<ArrayList<Integer>> episodes =
            new MutableLiveData<ArrayList<Integer>>();

    public LiveData<ArrayList<Integer>> getEpisodeIds(int id) {
        getIdsEpisodes(id);
        return episodes;
    }

    @SuppressLint("CheckResult")
    public void getIdsEpisodes(int id) {

//        NetworkController.INSTANCE.getRickAndMortyApi().getCharacter(id)
        api.getCharacter(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            episodes.setValue(getIds(response.body().getEpisode()));
                            loadEpisodes();
                        }, Throwable::printStackTrace
                )
        ;
    }

    private MutableLiveData<ArrayList<EpisodesResult>> listOfEpisodes;

    public LiveData<ArrayList<EpisodesResult>> getEpisodesForCharacterDetails() {
        if (listOfEpisodes == null) {
            listOfEpisodes = new MutableLiveData<ArrayList<EpisodesResult>>();
            loadEpisodes();
        }
        return listOfEpisodes;
    }

    @SuppressLint("CheckResult")
    public void loadEpisodes() {

//        NetworkController.INSTANCE.getRickAndMortyApi().getMultiEpisodes(episodes.getValue())
        api.getMultiEpisodes(episodes.getValue())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> listOfEpisodes.setValue(response.body()), Throwable::printStackTrace);
    }

    private ArrayList<Integer> getIds(List<String> list) {
        ArrayList<Integer> integerArrayList = new ArrayList<Integer>();
        int var2 = 0;
        int var3 = list.size();

        while (var2 < var3) {
            int i = var2++;
            switch (list.get(i).length()) {
                case 41:
                    integerArrayList.add(Integer.parseInt(StringsKt.takeLast((String) list.get(i), 1)));
                    break;
                case 42:
                    integerArrayList.add(Integer.parseInt(StringsKt.takeLast((String) list.get(i), 2)));
            }
        }
        return integerArrayList;
    }


}







