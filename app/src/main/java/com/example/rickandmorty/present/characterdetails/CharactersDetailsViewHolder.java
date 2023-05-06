package com.example.rickandmorty.present.characterdetails;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmort.R;

public class CharactersDetailsViewHolder extends RecyclerView.ViewHolder {
    TextView episodeName,episodeNumber,episodeDateRelease;

    public CharactersDetailsViewHolder(@NonNull View itemView) {
        super(itemView);
        episodeName = itemView.findViewById(R.id.episode_name);
        episodeNumber = itemView.findViewById(R.id.episode_number);
        episodeDateRelease = itemView.findViewById(R.id.episode_date_release);

    }
}
