package com.example.rickandmorty.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmort.R
import com.example.rickandmort.databinding.FragmentEpisodeDetailsBinding
import com.example.rickandmort.databinding.FragmentEpisodesBinding
import com.example.rickandmorty.api.CharactersResult
import com.example.rickandmorty.ui.charactersadapter.CharactersAdapter


class EpisodeDetailsFragment : Fragment() {
    private lateinit var binding: FragmentEpisodeDetailsBinding
    private val sharedViewModel: EpisodesViewModel by activityViewModels()
    private val viewModel by viewModels<EpisodeDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.run {
//            episodeName.text = sharedViewModel.episode.value?.name
//            episodeNumber.text = sharedViewModel.episode.value?.episode
//            episodeDateRelease.text = sharedViewModel.episode.value?.air_date
//            episodeCreated.text = sharedViewModel.episode.value?.created
//            episodeUrl.text = sharedViewModel.episode.value?.url
//        }
    }

    private suspend fun initAdapter(list: ArrayList<CharactersResult>) {
        binding.recyclerEpisode.run {
            addItemDecoration()
            if (adapter == null) {
                adapter = CharactersAdapter()

                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
            (adapter as? CharactersAdapter)?.setList(list)
        }


    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun addItemDecoration() {
        val itemMargin = RecyclerMargin()
        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.divider_drawable))
        binding.recyclerEpisode.run {
            addItemDecoration(dividerItemDecoration)
            addItemDecoration(itemMargin)
        }
    }


}