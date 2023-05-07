package com.example.rickandmorty.data.local.paging.datasource

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmorty.data.model.LocationsEntity
import com.example.rickandmorty.data.remove.service.model.LocationsResult

class LocationsDiffUtil : DiffUtil.ItemCallback<LocationsEntity>() {
    override fun areItemsTheSame(oldItem: LocationsEntity, newItem: LocationsEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LocationsEntity, newItem: LocationsEntity): Boolean {
        return oldItem == newItem
    }
}