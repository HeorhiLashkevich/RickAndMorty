package com.example.rickandmorty.ui.locationdetails;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.rickandmort.R;
import com.example.rickandmorty.api.CharactersResult;
import com.squareup.picasso.Picasso;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class LocationDetailsAdapter extends RecyclerView.Adapter<LocationDetailsViewHolder> {
    List<CharactersResult> list;
    private final PublishSubject<CharactersResult> mViewClickSubject = PublishSubject.create();
    public Observable<CharactersResult> getViewClickedObservable() {
        return mViewClickSubject.observeOn(Schedulers.io());
    }

    public LocationDetailsAdapter(List<CharactersResult> items) {
        this.list = items;

    }
    @NonNull
    @Override
    public LocationDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LocationDetailsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_characters, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LocationDetailsViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.characterName.setText(list.get(position).getName());
        holder.characterSpecies.setText(list.get(position).getSpecies());
        holder.characterGender.setText(list.get(position).getGender());
        holder.characterStatus.setText(list.get(position).getStatus());
        Picasso.get().load(list.get(position).getImage()).into(holder.characterImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewClickSubject.onNext(list.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
