package com.example.rickandmorty.data.mapper

import com.example.rickandmorty.data.model.CharactersEntity
import com.example.rickandmorty.data.remove.service.model.CharactersResult

fun CharactersResult.toCharactersEntity() : CharactersEntity {
    return CharactersEntity(
        id = this.id,
        name = this.name,
        created = this.created,
        episode = this.episode,
        origin = this.origin,
        location = this.location,
        gender = this.gender,
        image = this.image,
        species = this.species,
        status = this.status,
        type = this.type,
        url = this.url
    )
}