package com.example.rickandmorty.present.locations

import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmort.databinding.ItemLocationsBinding
import com.example.rickandmorty.data.model.LocationsEntity

class LocationsViewHolder(
    private val binding: ItemLocationsBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: LocationsEntity) {
        binding.run {
            locationName.text = item.name
            locationType.text = item.type
            locationDimension.text = item.dimension
        }
    }
}

