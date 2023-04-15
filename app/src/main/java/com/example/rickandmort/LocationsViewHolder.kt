package com.example.rickandmort

import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmort.api.LocationsResult
import com.example.rickandmort.databinding.ItemLocationBinding

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

