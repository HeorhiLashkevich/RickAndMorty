package com.example.rickandmorty.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.api.LocationsResult
import com.example.rickandmorty.api.NetworkController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationsViewModel : ViewModel() {

    val listLocations = MutableLiveData<ArrayList<LocationsResult>>()

    fun getLocations() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = NetworkController.getRickAndMortyApi().getLocations()
            if (response.isSuccessful) {
                listLocations.postValue(response.body()?.results)
            }
        }

    }
}