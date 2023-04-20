package com.example.rickandmorty.ui.characterddetails;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmort.R;

public class CharactersDetailsViewHolder extends RecyclerView.ViewHolder {
    TextView locationName,locationType,locationDimension;



    public CharactersDetailsViewHolder(@NonNull View itemView) {
        super(itemView);
        locationName = itemView.findViewById(R.id.location_name);
        locationType = itemView.findViewById(R.id.location_type);
        locationDimension = itemView.findViewById(R.id.location_dimension);

    }
}
