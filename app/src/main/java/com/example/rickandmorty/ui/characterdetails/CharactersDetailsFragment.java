package com.example.rickandmorty.ui.characterdetails;

import static com.example.rickandmorty.ConstansKt.KEY_TO_CHARACTER_DETAILS;
import static com.example.rickandmorty.ConstansKt.KEY_TO_EPISODE_DETAILS;
import static com.example.rickandmorty.ConstansKt.KEY_TO_LOCATION_DETAILS;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmort.R;
import com.example.rickandmort.databinding.FragmentCharacterDetailsBinding;
import com.example.rickandmorty.CharactersItemClickListener;
import com.example.rickandmorty.api.CharactersResult;
import com.example.rickandmorty.api.EpisodesResult;
import com.example.rickandmorty.ui.episodes.EpisodesFragment;
import com.example.rickandmorty.ui.episodesdetails.EpisodeDetailsFragment;
import com.example.rickandmorty.ui.locationdetails.LocationsDetailsFragment;
import com.example.rickandmorty.ui.locations.LocationsFragment;

import java.util.ArrayList;
import java.util.List;

import kotlin.text.StringsKt;


public class CharactersDetailsFragment extends Fragment implements CharactersItemClickListener {

    private CharactersDetailsViewModel viewModel;
    private FragmentCharacterDetailsBinding binding;
    private int characterId;
    private CharactersDetailsAdapter adapter;
    private RecyclerView recyclerView;

    public CharactersDetailsFragment() {
        super(R.layout.fragment_character_details);
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        viewModel = new ViewModelProvider(this, getDefaultViewModelProviderFactory())
                .get(CharactersDetailsViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            characterId = getArguments().getInt(KEY_TO_CHARACTER_DETAILS, 0);
        }


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getCharacterValue(characterId).observe(getViewLifecycleOwner(), new Observer<CharactersResult>() {
            @Override
            public void onChanged(CharactersResult charactersResult) {
                binding.nameCharacter.setText(viewModel.getCharacterValue(characterId).getValue().getName());
                binding.genderCharacter.setText(viewModel.getCharacterValue(characterId).getValue().getGender());
                binding.originCharacter.setText(viewModel.getCharacterValue(characterId).getValue().getOrigin().getName());
                binding.statusCharacter.setText(viewModel.getCharacterValue(characterId).getValue().getStatus());
                binding.locationCharacter.setText(viewModel.getCharacterValue(characterId).getValue().getLocation().getName());
                binding.speciesCharacter.setText(viewModel.getCharacterValue(characterId).getValue().getSpecies());
                binding.originCharacter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onLocationClick(getId(viewModel.getCharacterValue(characterId).getValue().getLocation().getUrl()));
                    }
                });
                binding.locationCharacter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onLocationClick(getId(viewModel.getCharacterValue(characterId).getValue().getLocation().getUrl()));
                    }
                });

            }
        });
        viewModel.getEpisodesForCharacterDetails().observe(getViewLifecycleOwner(), new Observer<ArrayList<EpisodesResult>>() {
            @SuppressLint("CheckResult")
            @Override
            public void onChanged(ArrayList<EpisodesResult> episodesResults) {
                recyclerView = view.findViewById(R.id.episodes_from_character_recycler);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter = new CharactersDetailsAdapter(episodesResults);
                adapter.getViewClickedObservable().subscribe(view -> onItemClickListener(view.getId()));
                recyclerView.setAdapter(adapter);
                recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
            }
        });
        viewModel.getIdsEpisodes(characterId);


    }

    private Integer getId(String locationName) {
        int id = 0;
        switch (locationName.length()) {
            case 42:
                id = (Integer.parseInt(StringsKt.takeLast((String) locationName, 1)));
                break;
            case 43:
                id = (Integer.parseInt(StringsKt.takeLast((String) locationName, 2)));
                break;
            case 44:
                id = (Integer.parseInt(StringsKt.takeLast((String) locationName, 3)));

        }
        return id;

    }

    public void onLocationClick(int id) {
        Bundle bundle = new Bundle();
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        LocationsDetailsFragment locationsDetailsFragment = new LocationsDetailsFragment();
        bundle.putInt(KEY_TO_LOCATION_DETAILS, id);
        locationsDetailsFragment.setArguments(bundle);
        ft.replace(R.id.container, locationsDetailsFragment).commit();
    }

    @Override
    public void onItemClickListener(int id) {
        Bundle bundle = new Bundle();
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        EpisodeDetailsFragment episodeDetailsFragment = new EpisodeDetailsFragment();
        bundle.putInt(KEY_TO_EPISODE_DETAILS, id);
        episodeDetailsFragment.setArguments(bundle);
        ft.replace(R.id.container, episodeDetailsFragment).commit();
    }


}

