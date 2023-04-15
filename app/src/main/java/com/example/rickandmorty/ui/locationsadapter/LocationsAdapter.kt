package com.example.rickandmorty.ui.locationsadapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmort.databinding.ItemLocationBinding
import com.example.rickandmorty.api.LocationsResult

class LocationsAdapter() : RecyclerView.Adapter<LocationsViewHolder>() {
    private var list = arrayListOf<LocationsResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        return LocationsViewHolder(
            ItemLocationBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: ArrayList<LocationsResult>) {
        this.list = list
        notifyDataSetChanged()

    }

}

