package com.example.rickandmorty.ui.episodes

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmort.R
import com.example.rickandmort.databinding.FragmentEpisodesBinding
import com.example.rickandmorty.KEY_TO_CHARACTER_DETAILS
import com.example.rickandmorty.KEY_TO_EPISODE_DETAILS
import com.example.rickandmorty.api.EpisodesResult
import com.example.rickandmorty.api.NetworkController
import com.example.rickandmorty.ui.RecyclerMargin
import com.example.rickandmorty.ui.characterdetails.CharactersDetailsFragment
import com.example.rickandmorty.ui.episodesdetails.EpisodeDetailsFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class EpisodesFragment : Fragment() {

    private lateinit var binding: FragmentEpisodesBinding
    private val viewModel: EpisodesViewModel by viewModels()


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
                    val bundle = Bundle()
                    bundle.putInt(KEY_TO_EPISODE_DETAILS, it)
                    val episodeDetailsFragment = EpisodeDetailsFragment()
                    episodeDetailsFragment.arguments = bundle
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container, episodeDetailsFragment)
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


