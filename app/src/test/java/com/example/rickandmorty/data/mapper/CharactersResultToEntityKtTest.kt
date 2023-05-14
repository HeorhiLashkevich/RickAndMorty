package com.example.rickandmorty.data.mapper

import com.example.rickandmorty.data.model.CharactersEntity
import com.example.rickandmorty.domain.model.CharactersResult
import com.example.rickandmorty.domain.model.Location
import com.example.rickandmorty.domain.model.Origin
import io.mockk.mockk
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

internal class CharactersResultToEntityKtTest {


    @Test
    fun charactersResult() {
        val char1 = CharactersResult(
            name = "Rick",
            gender = "Male",
            species = "we",
            origin = Origin(name = "qwe", url = "wer"),
            status = "dead",
            created = "qwe",
            episode = listOf(),
            location = Location(name = "qwe", url = "weg"),
            image = "ds",
            id = 1,
            type = "erg",
            url = "wef"
        )
        val char2 = CharactersEntity(
            name = "Rick",
            gender = "Male",
            species = "we",
            origin = Origin(name = "qwe", url = "wer"),
            status = "dead",
            created = "qwe",
            episode = listOf(),
            location = Location(name = "qwe", url = "weg"),
            image = "ds",
            id = 1,
            type = "erg",
            url = "wef"
        )
        assertEquals(char1.toCharactersEntity().name, char2.name)
    }






}