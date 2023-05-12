package com.example.rickandmorty.data.mapper

import com.example.rickandmorty.data.model.EpisodeEntity
import com.example.rickandmorty.data.remove.service.model.EpisodesResult

fun EpisodesResult.toEpisodesEntity(): EpisodeEntity {
    return EpisodeEntity(
        id = this.id.toLong(),
        air_date = this.air_date,
        characters = this.characters,
        created = this.created,
        episode = this.episode,
        name = this.name,
        url = this.url
    )
}