package com.example.rickandmorty.ui.characterddetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmort.R;
import com.example.rickandmorty.api.EpisodesResult;
import com.example.rickandmorty.api.LocationsResult;

import java.util.List;

public class CharactersDetailsAdapter extends RecyclerView.Adapter<CharactersDetailsViewHolder> {


    List<EpisodesResult> items;

    public CharactersDetailsAdapter(List<EpisodesResult> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CharactersDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CharactersDetailsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_episodes, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CharactersDetailsViewHolder holder, int position) {
      holder.episodeName.setText(items.get(position).getName());
      holder.episodeNumber.setText(items.get(position).getEpisode());
      holder.episodeDateRelease.setText(items.get(position).getAir_date());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
