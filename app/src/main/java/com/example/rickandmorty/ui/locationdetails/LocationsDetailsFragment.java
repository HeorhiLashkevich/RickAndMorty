package com.example.rickandmorty.ui.locationdetails;

import static com.example.rickandmorty.ConstansKt.KEY_FROM_LOCATION_TO_LOCATIONDETAILS;

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
import com.example.rickandmort.databinding.FragmentLocationDetailsBinding;
import com.example.rickandmorty.api.CharactersResult;
import com.example.rickandmorty.api.LocationsResult;
import com.example.rickandmorty.ui.characterdetails.CharactersDetailsAdapter;

import java.util.ArrayList;
import java.util.Objects;


public class LocationsDetailsFragment extends Fragment {
    private LocationDetailsViewModel viewModel;
    private int locationId;
    private FragmentLocationDetailsBinding binding;
    private LocationsDetailsAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        viewModel = new ViewModelProvider(this, getDefaultViewModelProviderFactory())
                .get(LocationDetailsViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            locationId = getArguments().getInt(KEY_FROM_LOCATION_TO_LOCATIONDETAILS, 0);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getCurrentLocation(locationId).observe(getViewLifecycleOwner(), new Observer<LocationsResult>() {
            @Override
            public void onChanged(LocationsResult locationsResult) {
                binding.nameLocation.setText(Objects.requireNonNull(viewModel.getCurrentLocation(locationId).getValue()).getName());
                binding.dimensionLocation .setText(Objects.requireNonNull(viewModel.getCurrentLocation(locationId).getValue()).getDimension());
                binding.typeLocation .setText(Objects.requireNonNull(viewModel.getCurrentLocation(locationId).getValue()).getType());
                binding.createLocation.setText(Objects.requireNonNull(viewModel.getCurrentLocation(locationId).getValue()).getCreated());
            }
        });
        viewModel.getCharactersFromCurrentEpisode().observe(getViewLifecycleOwner(), new Observer<ArrayList<CharactersResult>>() {
            @Override
            public void onChanged(ArrayList<CharactersResult> charactersResults) {
                recyclerView = view.findViewById(R.id.recycler_characters_from_location);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter = new LocationsDetailsAdapter(charactersResults);
                recyclerView.setAdapter(adapter);
                recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
            }
        });
        viewModel.getCharactersIds(locationId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLocationDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
