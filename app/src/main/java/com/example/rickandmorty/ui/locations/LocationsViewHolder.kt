package com.example.rickandmorty.ui.locations

import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmort.databinding.ItemLocationsBinding
import com.example.rickandmorty.api.LocationsResult

class LocationsViewHolder(
    private val binding: ItemLocationsBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: LocationsResult) {
        binding.run {
            locationName.text = item.name
            locationType.text = item.type
            locationDimension.text = item.dimension
        }
    }
}

