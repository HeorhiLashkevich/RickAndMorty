package com.example.rickandmorty.present.locationdetails;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.rickandmort.R;


public class LocationDetailsViewHolder extends RecyclerView.ViewHolder {

    TextView characterName,characterSpecies, characterGender, characterStatus;
    ImageView characterImage;

    public LocationDetailsViewHolder(@NonNull View itemView) {
        super(itemView);

        characterName = itemView.findViewById(R.id.character_name);
        characterSpecies = itemView.findViewById(R.id.character_species);
        characterGender = itemView.findViewById(R.id.character_gender);
        characterStatus = itemView.findViewById(R.id.character_status);
        characterImage = itemView.findViewById(R.id.character_image);
    }
}
