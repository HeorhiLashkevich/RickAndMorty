package com.example.rickandmorty.data.mapper

import com.example.rickandmorty.data.model.EpisodeEntity
import com.example.rickandmorty.data.remove.service.model.EpisodesResult
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

internal class EpisodesResultToEntityKtTest {

    @Test
    fun episodesResult() {
        val char1 = EpisodesResult(
            name = "Rick",
            air_date = "last",
            created = "qwe",
            episode = "listOf()",
            id = 1,
            characters = listOf(),
            url = "wef"
        )
        val char2 = EpisodeEntity(
            name = "Rick",
            air_date = "last",
            created = "qwe",
            episode = "listOf()",
            id = 1,
            characters = listOf(),
            url = "wef"
        )
        assertEquals(char1.toEpisodesEntity().name, char2.name)
    }
}
