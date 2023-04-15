package com.example.rickandmorty.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmort.databinding.FragmentEpisodesBinding
import com.example.rickandmorty.api.EpisodesResult
import com.example.rickandmorty.ui.episodesadapter.EpisodesPagingAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class EpisodesFragment : Fragment() {

    private lateinit var binding: FragmentEpisodesBinding

    private val viewModel by viewModels<EpisodesViewModel>()

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
                setList(it)
            }
        }
    }

    private suspend fun setList(list: PagingData<EpisodesResult>) {
        binding.recyclerEpisodes.run {

            if (adapter == null) {
                adapter = EpisodesPagingAdapter()
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
            (adapter as? EpisodesPagingAdapter)?.submitData(list)
        }


    }
}
