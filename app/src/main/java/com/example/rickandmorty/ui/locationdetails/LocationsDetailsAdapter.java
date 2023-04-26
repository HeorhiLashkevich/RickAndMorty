package com.example.rickandmorty.ui.locationdetails;



import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmort.R;
import com.example.rickandmorty.api.CharactersResult;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LocationsDetailsAdapter extends RecyclerView.Adapter<LocationsDetailsViewHolder> {


    List<CharactersResult> items;

    public LocationsDetailsAdapter(List<CharactersResult> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public LocationsDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LocationsDetailsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_characters,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LocationsDetailsViewHolder holder, int position) {
        holder.characterName.setText(items.get(position).getName());
        holder.characterSpecies.setText(items.get(position).getSpecies());
        holder.characterGender.setText(items.get(position).getGender());
        holder.characterStatus.setText(items.get(position).getStatus());
        Picasso.get().load(items.get(position).getImage()).into(holder.characterImage);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
