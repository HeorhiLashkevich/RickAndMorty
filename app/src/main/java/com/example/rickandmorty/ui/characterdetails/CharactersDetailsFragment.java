package com.example.rickandmorty.ui.characterdetails;

import static com.example.rickandmorty.ui.episodesdetails.EpisodeDetailsFragmentKt.KEY_FROM_EPISODE_TO_CHARACTER;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmort.R;
import com.example.rickandmort.databinding.FragmentCharacterDetailsBinding;
import com.example.rickandmorty.api.CharactersResult;
import com.example.rickandmorty.api.EpisodesResult;

import java.util.ArrayList;


public class CharactersDetailsFragment extends Fragment {

    private CharactersDetailsViewModel viewModel;
    private FragmentCharacterDetailsBinding binding;
    private int characterID;
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
            characterID = getArguments().getInt(KEY_FROM_EPISODE_TO_CHARACTER, 0);
        }


    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.getCharacterValue(characterID).observe(getViewLifecycleOwner(), new Observer<CharactersResult>() {
            @Override
            public void onChanged(CharactersResult charactersResult) {
                binding.nameCharacter.setText(viewModel.getCharacterValue(characterID).getValue().getName());
                binding.genderCharacter.setText(viewModel.getCharacterValue(characterID).getValue().getGender());
                binding.originCharacter.setText(viewModel.getCharacterValue(characterID).getValue().getOrigin().getName());
                binding.statusCharacter.setText(viewModel.getCharacterValue(characterID).getValue().getStatus());
                binding.locationCharacter.setText(viewModel.getCharacterValue(characterID).getValue().getLocation().getName());
                binding.speciesCharacter.setText(viewModel.getCharacterValue(characterID).getValue().getSpecies());

            }
        });
        viewModel.getEpisodesForCharacterDetails().observe(getViewLifecycleOwner(), new Observer<ArrayList<EpisodesResult>>() {
            @Override
            public void onChanged(ArrayList<EpisodesResult> episodesResults) {
                recyclerView = view.findViewById(R.id.episodes_from_character_recycler);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter = new CharactersDetailsAdapter(episodesResults);
                recyclerView.setAdapter(adapter);
                recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
            }
        });
        viewModel.getIdsEpisodes(characterID);
//        viewModel.getEpisodesForCharacterDetails();




    }

    @Nullable

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();


    }


}

