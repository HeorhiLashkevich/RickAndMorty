package com.example.rickandmorty.ui.characterddetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmort.R;
import com.example.rickandmort.databinding.FragmentCharactersBinding;
import com.example.rickandmorty.api.EpisodesResult;
import com.example.rickandmorty.api.LocationsResult;

import java.util.Collections;
import java.util.List;

public class CharactersDetailsFragment extends Fragment {

    private List<LocationsResult> list;
    private FragmentCharactersBinding binding;
    private CharactersDetailsAdapter adapter;
    private RecyclerView recyclerView;

    public CharactersDetailsFragment() {
        super(R.layout.fragment_character_details);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Nullable

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LocationsResult locationsResult = new LocationsResult("qw", "wr", 23, "erg", Collections.singletonList("fb"), "erg", "wef");
        list.add(locationsResult);
        View view = inflater.inflate(R.layout.fragment_character_details, container, false);
        adapter = new CharactersDetailsAdapter(list);
        recyclerView = view.findViewById(R.id.episodes_from_character_recycler);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        return binding.getRoot();


    }


}

