package com.example.rickandmorty.ui.locationsadapter

import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmort.databinding.ItemLocationBinding
import com.example.rickandmorty.api.LocationsResult

class LocationsViewHolder(
    private val binding: ItemLocationBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: LocationsResult) {
        binding.run {
            locationName.text = item.name
            locationType.text = item.type
            locationDimension.text = item.dimension
        }
    }
}

