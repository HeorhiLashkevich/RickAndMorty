package com.example.rickandmorty.ui.characterddetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmort.R;
import com.example.rickandmorty.api.LocationsResult;

import java.util.List;

public class CharactersDetailsAdapter extends RecyclerView.Adapter<CharactersDetailsViewHolder> {


    List<LocationsResult> items;

    public CharactersDetailsAdapter(List<LocationsResult> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CharactersDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CharactersDetailsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_locations, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CharactersDetailsViewHolder holder, int position) {
        holder.locationName.setText(items.get(position).getName());
        holder.locationType.setText(items.get(position).getType());
        holder.locationDimension.setText(items.get(position).getDimension());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
