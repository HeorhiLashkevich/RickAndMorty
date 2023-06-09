package com.example.rickandmorty.present.locations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.rickandmort.databinding.ItemLocationsBinding
import com.example.rickandmorty.data.local.paging.datasource.LocationsDiffUtil
import com.example.rickandmorty.data.model.LocationsEntity


class LocationsPagingAdapter(
    private val onLocationClick: (id: Int) -> Unit

) : PagingDataAdapter<LocationsEntity, LocationsViewHolder>(LocationsDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        return LocationsViewHolder(
            ItemLocationsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        getItem(position)?.let { id ->
            holder.bind(id)
            holder.itemView.setOnClickListener {
                onLocationClick(id.id.toInt())
            }
        }
    }

}