package com.example.rickandmorty.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmort.R
import com.example.rickandmort.databinding.FragmentEpisodesBinding
import com.example.rickandmorty.api.EpisodesResult
import com.example.rickandmorty.ui.episodesadapter.EpisodesPagingAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class EpisodesFragment : Fragment() {

    private lateinit var binding: FragmentEpisodesBinding
    private val viewModel: EpisodesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodesBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.flow.collectLatest {
                initAdapter(it)
            }
        }
    }

    private suspend fun initAdapter(list: PagingData<EpisodesResult>) {
        binding.recyclerEpisodes.run {
            addItemDecoration()
            if (adapter == null) {
                adapter = EpisodesPagingAdapter {
                    viewModel.getEpisode(it)
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container, EpisodeDetailsFragment())
                        .addToBackStack("")
                        .commit()
                }
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
            (adapter as? EpisodesPagingAdapter)?.submitData(list)
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun addItemDecoration() {
        val itemMargin = RecyclerMargin()
        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.divider_drawable))
        binding.recyclerEpisodes.run {
            addItemDecoration(dividerItemDecoration)
            addItemDecoration(itemMargin)
        }
    }
}
