package com.example.rickandmorty.data.mapper

import com.example.rickandmorty.data.model.LocationsEntity
import com.example.rickandmorty.data.remove.service.model.LocationsResult

fun LocationsResult.toLocationsEntity(): LocationsEntity {
    return LocationsEntity(
        id = this.id.toLong(),
        created = this.created,
        dimension = this.dimension,
        name = this.name,
        residents = this.residents,
        type = this.type,
        url = this.url
    )
}