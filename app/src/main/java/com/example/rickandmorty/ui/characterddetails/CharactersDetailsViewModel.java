package com.example.rickandmorty.ui.characterddetails;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.api.CharactersResult;
import com.example.rickandmorty.api.EpisodesResult;
import com.example.rickandmorty.api.NetworkController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.RandomAccess;


import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.schedulers.Schedulers;


public class CharactersDetailsViewModel extends ViewModel {
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
        NetworkController.INSTANCE.getRickAndMortyApi().getCharacter(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> character.setValue(response.body())
                )
        ;
    }

    private MutableLiveData<ArrayList<EpisodesResult>> listOfEpisodes;

    public LiveData<ArrayList<EpisodesResult>> getEpisodesForCharacterDetails(int id) {
        if (listOfEpisodes == null) {
            listOfEpisodes = new MutableLiveData<ArrayList<EpisodesResult>>();

            loadEpisodes(episodes.getValue());
        }
        return listOfEpisodes;
    }

    @SuppressLint("CheckResult")
    public void loadEpisodes(ArrayList<Integer> list) {
        NetworkController.INSTANCE.getRickAndMortyApi().getMultiEpisodes(list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> listOfEpisodes.setValue(response.body()), Throwable::printStackTrace);
    }

//    private MutableLiveData<ArrayList<Integer>> ids =
//            new MutableLiveData<ArrayList<Integer>>();
//
//    public LiveData<ArrayList<Integer>> getIds(int id) {
//        getCharactersIds(getEpisodeStrings(id).getValue());
//        return ids;
//    }
//
//
//    public ArrayList<Integer> getCharactersIds(ArrayList<Integer> list) {
//        ArrayList<Integer> integerArrayList = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
//
//        return integerArrayList;
//
//    }

    public MutableLiveData<ArrayList<Integer>> episodes =
            new MutableLiveData<ArrayList<Integer>>();

    public LiveData<ArrayList<Integer>> getEpisodeStrings(int id) {
        getStringsEpisodes(id);
        return episodes;
    }

    @SuppressLint("CheckResult")
    public void getStringsEpisodes(int id) {
        ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(3,4,1,2,5,6));
        episodes.setValue(list);
        NetworkController.INSTANCE.getRickAndMortyApi().getCharacter(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response ->
                    episodes.setValue(getIdssss(response.body().getEpisode()))

                )
        ;

    }

    private ArrayList<Integer> getIdssss(List<String> list) {
        ArrayList<Integer> integerArrayList = new ArrayList<Integer>();
        for (int i = 0; i < list.size() - 1; i++) {
            if (Integer.valueOf(list.get(i).substring(40)).toString().length() == 1) {
                integerArrayList.add(Integer.valueOf(list.get(i).substring(40)));
            } else if (Integer.valueOf(list.get(i).substring(40)).toString().length() == 2) {
                integerArrayList.add(Integer.valueOf(list.get(i).substring(40)));
            } else break;
        }
        return integerArrayList;
    }
//    private ArrayList<Integer> getIdssss(List<String> list) {
//        ArrayList<Integer> integerArrayList = new ArrayList<Integer>();
//        for (int i = 0; i < list.size() - 1; i++) {
//            if (Integer.valueOf(list.get(i).substring(40)).toString().length() == 1) {
//                integerArrayList.add(Integer.valueOf(list.get(i).substring(40)));
//            } else if (Integer.valueOf(list.get(i).substring(40)).toString().length() == 2) {
//                integerArrayList.add(Integer.valueOf(list.get(i).substring(40)));
//            } else break;
//        }
//        return integerArrayList;
//    }


}






