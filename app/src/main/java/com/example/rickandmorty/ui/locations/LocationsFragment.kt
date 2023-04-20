package com.example.rickandmorty.ui.locations

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmort.R
import com.example.rickandmort.databinding.FragmentLocationsBinding
import com.example.rickandmorty.api.LocationsResult
import com.example.rickandmorty.ui.RecyclerMargin
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LocationsFragment : Fragment() {

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
        lifecycleScope.launch {
            viewModel.flow.collectLatest {
                setList(it)
            }
        }

    }

    private suspend fun setList(list: PagingData<LocationsResult>) {
        binding.recyclerLocations.run {
            addItemDecoration()
            if (adapter == null) {
                adapter = LocationsPagingAdapter()
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
            (adapter as? LocationsPagingAdapter)?.submitData(list)
        }


    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun addItemDecoration() {
        val itemMargin = RecyclerMargin()
        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.divider_drawable))
        binding.recyclerLocations?.run {
            addItemDecoration(dividerItemDecoration)
            addItemDecoration(itemMargin)
        }
    }
}