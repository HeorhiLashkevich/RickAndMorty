package com.example.rickandmorty.data.local

import androidx.room.TypeConverter
import com.example.rickandmorty.api.Location
import com.example.rickandmorty.api.Origin
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class TypeConverter {


    @TypeConverter
    fun fromListEpisodes(episodeList: List<String?>?): String? {
        if (episodeList == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<String?>?>() {}.type
        return gson.toJson(episodeList, type)
    }

    @TypeConverter
    fun toListEpisodes(episode: String?): List<String>? {
        if (episode == null) {
            return null
        }

        val gson = Gson()
        val type: Type = object : TypeToken<List<String?>?>() {}.type
        return gson.fromJson<List<String>>(episode, type)
    }


    @TypeConverter
    fun fromLocation(location: Location?): String? {
        if (location == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<Location?>() {}.type
        return gson.toJson(location, type)
    }

    @TypeConverter
    fun toLocation(location: String?): Location? {
        if (location == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<Location?>() {}.type
        return gson.fromJson<Location>(location, type)
    }

    @TypeConverter
    fun fromOrigin(origin: Origin?): String? {
        if (origin == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<Origin?>() {}.type
        return gson.toJson(origin, type)
    }


    @TypeConverter
    fun toOrigin(origin: String?): Origin? {
        if (origin == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<Origin?>() {}.type
        return gson.fromJson<Origin>(origin, type)
    }




}