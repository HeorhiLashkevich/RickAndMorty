package com.example.rickandmorty.present.episodes

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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmort.R
import com.example.rickandmort.databinding.FragmentEpisodesBinding
import com.example.rickandmorty.App
import com.example.rickandmorty.utils.KEY_TO_EPISODE_DETAILS
import com.example.rickandmorty.data.model.EpisodeEntity
import com.example.rickandmorty.present.episodesdetails.EpisodeDetailsFragment
import com.example.rickandmorty.utils.EPISODES_TAG
import com.example.rickandmorty.utils.RecyclerMargin
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodesFragment : Fragment() {

    private lateinit var binding: FragmentEpisodesBinding

    @Inject
    lateinit var viewModelProvider: EpisodesModelProvider
    private lateinit var viewModel: EpisodesViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelProvider).get(EpisodesViewModel::class.java)
    }


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
            viewModel.searchEpisodes().collectLatest {
                initAdapter(it)
            }
        }
    }

    private suspend fun initAdapter(list: PagingData<EpisodeEntity>) {
        binding.recyclerEpisodes.run {
            addItemDecoration()
            val episodeDetailsFragment = EpisodeDetailsFragment()

            if (adapter == null) {
                adapter = EpisodesPagingAdapter {
                    val bundle = Bundle()
                    bundle.putInt(KEY_TO_EPISODE_DETAILS, it)
                    episodeDetailsFragment.arguments = bundle
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container, episodeDetailsFragment)
                        .addToBackStack(EPISODES_TAG)
                        .commit()
                }
                layoutManager =
                    GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            }
            (adapter as? EpisodesPagingAdapter)?.submitData(list)
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun addItemDecoration() {
        val itemMargin = RecyclerMargin()
        val dividerItemDecoration2 = DividerItemDecoration(context, RecyclerView.HORIZONTAL)
        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.divider_drawable))
        binding.recyclerEpisodes.run {
            addItemDecoration(dividerItemDecoration)
            addItemDecoration(dividerItemDecoration2)
            addItemDecoration(itemMargin)
        }
    }
}


