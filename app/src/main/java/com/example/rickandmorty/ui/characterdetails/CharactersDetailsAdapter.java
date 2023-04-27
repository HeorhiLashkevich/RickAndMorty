package com.example.rickandmorty.ui.characterdetails;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.rickandmort.R;
import com.example.rickandmorty.api.CharactersResult;
import com.example.rickandmorty.api.EpisodesResult;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class CharactersDetailsAdapter extends RecyclerView.Adapter<CharactersDetailsViewHolder> {

    List<EpisodesResult> list;
    private final PublishSubject<EpisodesResult> mViewClickSubject = PublishSubject.create();

    public Observable<EpisodesResult> getViewClickedObservable() {
        return mViewClickSubject.observeOn(Schedulers.io());
    }

    public CharactersDetailsAdapter(List<EpisodesResult> items) {
        this.list = items;
    }

    @NonNull
    @Override
    public CharactersDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CharactersDetailsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_episodes, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CharactersDetailsViewHolder holder, @SuppressLint("RecyclerView") int position) {
      holder.episodeName.setText(list.get(position).getName());
      holder.episodeNumber.setText(list.get(position).getEpisode());
      holder.episodeDateRelease.setText(list.get(position).getAir_date());
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
