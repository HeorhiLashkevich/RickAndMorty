package com.example.rickandmorty.present.locations

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmort.R
import com.example.rickandmort.databinding.FragmentLocationsBinding
import com.example.rickandmorty.App
import com.example.rickandmorty.utils.KEY_TO_LOCATION_DETAILS
import com.example.rickandmorty.data.model.LocationsEntity
import com.example.rickandmorty.utils.RecyclerMargin
import com.example.rickandmorty.present.locationdetails.LocationDetailsFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationsFragment : Fragment() {

    private lateinit var binding: FragmentLocationsBinding
    @Inject
    lateinit var viewModelProvider: LocationsModelProvider
    private lateinit var viewModel: LocationsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelProvider).get(LocationsViewModel::class.java)
    }

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

    private suspend fun setList(list: PagingData<LocationsEntity>) {
        binding.recyclerLocations.run {
            addItemDecoration()
            val locationDetailsFragment =
                LocationDetailsFragment()

            if (adapter == null) {
                adapter = LocationsPagingAdapter{
                    val bundle = Bundle()
                    bundle.putInt(KEY_TO_LOCATION_DETAILS, it)
                    locationDetailsFragment.arguments = bundle
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container, locationDetailsFragment)
                        .addToBackStack("")
                        .commit()
                }
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
        binding.recyclerLocations.run {
            addItemDecoration(dividerItemDecoration)
            addItemDecoration(itemMargin)
        }
    }
}