package com.example.rickandmorty.ui.characterdetails;

import android.annotation.SuppressLint;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.rickandmorty.api.CharactersResult;
import com.example.rickandmorty.api.EpisodesResult;
import com.example.rickandmorty.api.NetworkController;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kotlin.text.StringsKt;


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

    public LiveData<ArrayList<Integer>> getEpisodeIds(int id) {
        getIdsEpisodes(id);
        return episodes;
    }

    @SuppressLint("CheckResult")
    public void getIdsEpisodes(int id) {
        NetworkController.INSTANCE.getRickAndMortyApi().getCharacter(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            ArrayList<Integer> list = getIdssss(response.body().getEpisode());
                            episodes.setValue(list);
                            loadEpisodes();
                        }
//                        episodes.setValue(new ArrayList<Integer>(getIdssss(response.body().getEpisode())))

                )
        ;
//        ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(3,4,1,2,5,6));
//        episodes.setValue(list);

    }

//    private MutableLiveData<List<String>> stringsEpisodes =
//            new MutableLiveData<List<String>>();
//
//    public LiveData<List<String>> getEpisodeStrings(int id) {
//        getStringIdsEpisodes(id);
//        return stringsEpisodes;
//    }
//
//    @SuppressLint("CheckResult")
//    public void getStringIdsEpisodes(int id) {
//        NetworkController.INSTANCE.getRickAndMortyApi().getCharacter(id)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(response ->
//                        stringsEpisodes.setValue((response.body().getEpisode())
//                        ))
//        ;
////        ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(3,4,1,2,5,6));
////        episodes.setValue(list);
//
//    }

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
        NetworkController.INSTANCE.getRickAndMortyApi().getMultiEpisodes(episodes.getValue())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> listOfEpisodes.setValue(response.body()), Throwable::printStackTrace);
    }

    private ArrayList<Integer> getIdssss(List<String> list) {
        ArrayList<Integer> integerArrayList = new ArrayList<Integer>();
        int var2 = 0;
        int var3 = list.size();

        while (var2 < var3) {
            int i = var2++;
            switch (((String) list.get(i)).length()) {
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






