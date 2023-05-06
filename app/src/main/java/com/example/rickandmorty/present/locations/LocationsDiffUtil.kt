package com.example.rickandmorty.present.locations

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmorty.api.LocationsResult

class LocationsDiffUtil : DiffUtil.ItemCallback<LocationsResult>() {
    override fun areItemsTheSame(oldItem: LocationsResult, newItem: LocationsResult): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LocationsResult, newItem: LocationsResult): Boolean {
        return oldItem == newItem
    }
}