package com.example.rickandmorty.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmort.databinding.FragmentLocationsBinding
import com.example.rickandmorty.api.LocationsResult
import com.example.rickandmorty.ui.locationsadapter.LocationsAdapter

class LocationsFragment  : Fragment() {

    private lateinit var binding: FragmentLocationsBinding

    private val viewModel by viewModels<LocationsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.listLocations.observe(viewLifecycleOwner) {
            setList(it)
        }
        viewModel.getLocations()
    }

    private fun setList(list: ArrayList<LocationsResult>) {
        binding.recyclerLocations.run {

            if (adapter == null) {
                adapter = LocationsAdapter()
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
            (adapter as? LocationsAdapter)?.setList(list)
        }


    }
}