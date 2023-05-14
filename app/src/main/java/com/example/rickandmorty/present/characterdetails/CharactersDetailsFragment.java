package com.example.rickandmorty.present.characterdetails;

import static com.example.rickandmorty.utils.ConstansKt.CHARACTERS_TAG;
import static com.example.rickandmorty.utils.ConstansKt.CHARACTER_DETAILS_TAG;
import static com.example.rickandmorty.utils.ConstansKt.EPISODE_DETAILS_TAG;
import static com.example.rickandmorty.utils.ConstansKt.KEY_TO_CHARACTER_DETAILS;
import static com.example.rickandmorty.utils.ConstansKt.KEY_TO_EPISODE_DETAILS;
import static com.example.rickandmorty.utils.ConstansKt.KEY_TO_LOCATION_DETAILS;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.rickandmort.R;
import com.example.rickandmort.databinding.FragmentCharacterDetailsBinding;
import com.example.rickandmorty.App;
import com.example.rickandmorty.present.episodesdetails.EpisodeDetailsFragment;
import com.example.rickandmorty.present.locationdetails.LocationDetailsFragment;
import com.example.rickandmorty.domain.model.CharactersResult;
import com.example.rickandmorty.data.remove.service.model.EpisodesResult;
import com.example.rickandmorty.utils.CharactersItemClickListener;

import java.util.ArrayList;

import javax.inject.Inject;

import kotlin.text.StringsKt;


public class CharactersDetailsFragment extends Fragment implements CharactersItemClickListener {

    @Inject
    CharactersDetailsModelProvider charactersDetailsModelProvider;
     private CharactersDetailsViewModel viewModel;
    private FragmentCharacterDetailsBinding binding;
    private int characterId;
    private CharactersDetailsAdapter adapter;
    private RecyclerView recyclerView;



    public CharactersDetailsFragment() {
        super(R.layout.fragment_character_details);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        App.appComponent.inject(this);
        if (getArguments() != null) {
            characterId = getArguments().getInt(KEY_TO_CHARACTER_DETAILS, 0);
        }
        viewModel = new ViewModelProvider( this,  charactersDetailsModelProvider).get(CharactersDetailsViewModel.class);


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
    binding.charactersDetailsBackButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View arg0) {
            if (getParentFragmentManager().getBackStackEntryCount() != 0) {
                getParentFragmentManager().popBackStack();

            }
        }

    });

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
                        if (viewModel.getCharacterValue(characterId).getValue().getOrigin().getName().equalsIgnoreCase("unknown")) {
                            Toast.makeText(getContext(), "don't exist", Toast.LENGTH_LONG).show();
                        } else {
                            onLocationClick(getId(viewModel.getCharacterValue(characterId).getValue().getOrigin().getUrl()));
                        }
                    }
                });
                binding.locationCharacter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (viewModel.getCharacterValue(characterId).getValue().getLocation().getName().equalsIgnoreCase("unknown")) {
                            Toast.makeText(getContext(), "don't exist", Toast.LENGTH_LONG).show();
                        } else {
                            onLocationClick(getId(viewModel.getCharacterValue(characterId).getValue().getLocation().getUrl()));
                        }
                    }
                });

            }
        });
        viewModel.getEpisodesForCharacterDetails().observe(getViewLifecycleOwner(), new Observer<ArrayList<EpisodesResult>>() {
            @SuppressLint("CheckResult")
            @Override
            public void onChanged(ArrayList<EpisodesResult> episodesResults) {
                recyclerView = view.findViewById(R.id.episodes_from_character_recycler);
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2, GridLayoutManager.VERTICAL, false));

                adapter = new CharactersDetailsAdapter(episodesResults);
                adapter.getViewClickedObservable().subscribe(view -> onItemClickListener(view.getId()));
                recyclerView.setAdapter(adapter);
                recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
                recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.HORIZONTAL));

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
        LocationDetailsFragment locationDetailsFragment = new LocationDetailsFragment();
        bundle.putInt(KEY_TO_LOCATION_DETAILS, id);
        locationDetailsFragment.setArguments(bundle);
        ft.replace(R.id.container, locationDetailsFragment).commit();
    }

    @Override
    public void onItemClickListener(int id) {
        Bundle bundle = new Bundle();
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        EpisodeDetailsFragment episodeDetailsFragment = new EpisodeDetailsFragment();
        bundle.putInt(KEY_TO_EPISODE_DETAILS, id);
        episodeDetailsFragment.setArguments(bundle);
        ft.addToBackStack(EPISODE_DETAILS_TAG);
        ft.replace(R.id.container, episodeDetailsFragment).commit();
    }



}

