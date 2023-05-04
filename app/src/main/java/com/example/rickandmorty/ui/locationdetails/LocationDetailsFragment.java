package com.example.rickandmorty.ui.locationdetails;

import static com.example.rickandmorty.ConstansKt.KEY_TO_CHARACTER_DETAILS;
import static com.example.rickandmorty.ConstansKt.KEY_TO_LOCATION_DETAILS;

import android.annotation.SuppressLint;
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
import com.example.rickandmort.databinding.FragmentLocationDetailsBinding;
import com.example.rickandmorty.App;
import com.example.rickandmorty.util.LocationItemClickListener;
import com.example.rickandmorty.api.CharactersResult;
import com.example.rickandmorty.api.LocationsResult;
import com.example.rickandmorty.ui.characterdetails.CharactersDetailsFragment;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;


public class LocationDetailsFragment extends Fragment implements LocationItemClickListener {
    @Inject
    LocationDetailsModelProvider locationDetailsModelProvider;
    private LocationDetailsViewModel viewModel;
    private int locationId;
    private FragmentLocationDetailsBinding binding;
    private LocationDetailsAdapter adapter;
    private RecyclerView recyclerView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.appComponent.inject(this);
        if (getArguments() != null) {
            locationId = getArguments().getInt(KEY_TO_LOCATION_DETAILS, 0);
        }
        viewModel = new ViewModelProvider(this,locationDetailsModelProvider).get(LocationDetailsViewModel.class);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.locationsDetailsBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                assert getFragmentManager() != null;
                if (getFragmentManager().getBackStackEntryCount() != 0) {
                    getFragmentManager().popBackStack();
                }
            }

        });
        viewModel.getCurrentLocation(locationId).observe(getViewLifecycleOwner(), new Observer<LocationsResult>() {
            @Override
            public void onChanged(LocationsResult locationsResult) {
                binding.nameLocation.setText(Objects.requireNonNull(viewModel.getCurrentLocation(locationId).getValue()).getName());
                binding.dimensionLocation.setText(Objects.requireNonNull(viewModel.getCurrentLocation(locationId).getValue()).getDimension());
                binding.typeLocation.setText(Objects.requireNonNull(viewModel.getCurrentLocation(locationId).getValue()).getType());
                binding.createLocation.setText(Objects.requireNonNull(viewModel.getCurrentLocation(locationId).getValue()).getCreated());
            }
        });
        viewModel.getCharactersFromCurrentEpisode().observe(getViewLifecycleOwner(), new Observer<ArrayList<CharactersResult>>() {
            @SuppressLint("CheckResult")
            @Override
            public void onChanged(ArrayList<CharactersResult> charactersResults) {
                recyclerView = view.findViewById(R.id.recycler_characters_from_location);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter = new LocationDetailsAdapter(charactersResults);
                adapter.getViewClickedObservable().subscribe(view ->
                        onItemClickListener(view.getId())
                );
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


    @Override
    public void onItemClickListener(int id) {
        Bundle bundle = new Bundle();
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        CharactersDetailsFragment charactersDetailsFragment = new CharactersDetailsFragment();
        bundle.putInt(KEY_TO_CHARACTER_DETAILS, id);
        charactersDetailsFragment.setArguments(bundle);
        ft.replace(R.id.container, charactersDetailsFragment).commit();
    }
}
