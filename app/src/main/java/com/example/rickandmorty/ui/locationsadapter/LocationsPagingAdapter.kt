package com.example.rickandmorty.ui.locationsadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.rickandmort.databinding.ItemLocationsBinding
import com.example.rickandmorty.api.LocationsResult


class LocationsPagingAdapter : PagingDataAdapter<LocationsResult, LocationsViewHolder>(
    LocationsDiffUtil()
) {
    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        return LocationsViewHolder(
            ItemLocationsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

}