package com.example.rickandmorty.data.mapper


import com.example.rickandmorty.domain.model.LocationsResult
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

internal class LocationsResultToEntityKtTest {


    @Test
    fun locationsResult() {
        val char1 = LocationsResult(
            name = "Rick",
            created = "qwe",
            dimension = "wwer",
            id = 1,
            type = "erg",
            url = "wef",
            residents = listOf()
        )
        val char2 = LocationsResult(
            name = "Rick",
            created = "qwe",
            dimension = "wwer",
            id = 1,
            type = "erg",
            url = "wef",
            residents = listOf()
        )
        assertEquals(char1.toLocationsEntity() .name, char2.name)
    }

}