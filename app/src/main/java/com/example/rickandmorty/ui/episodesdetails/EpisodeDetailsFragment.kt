package com.example.rickandmorty.ui.episodesdetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmort.R
import com.example.rickandmort.databinding.FragmentEpisodeDetailsBinding
import com.example.rickandmorty.KEY_TO_CHARACTER_DETAILS
import com.example.rickandmorty.KEY_TO_EPISODE_DETAILS
import com.example.rickandmorty.api.CharactersResult
import com.example.rickandmorty.ui.RecyclerMargin
import com.example.rickandmorty.ui.characterdetails.CharactersDetailsFragment
import com.example.rickandmorty.ui.episodes.EpisodesViewModel


class EpisodeDetailsFragment : Fragment() {
    private lateinit var binding: FragmentEpisodeDetailsBinding
    private var episodeId = 0
    private val viewModel: EpisodeDetailsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            episodeId = requireArguments().getInt(KEY_TO_EPISODE_DETAILS, 0)
        }
    }


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

        viewModel.characters.observe(viewLifecycleOwner) {
            initAdapter(it)
        }

        viewModel.episode.observe(viewLifecycleOwner) {
            binding.run {
                episodeName.text = it.name
                episodeNumber.text = it.episode
                episodeDateRelease.text = it.air_date
                episodeCreated.text = it.created
                episodeUrl.text = it.url
            }
        }
        viewModel.getEpisode(episodeId)
    }

    private fun initAdapter(list: ArrayList<CharactersResult>) {
        binding.recyclerEpisode.run {
            addItemDecoration()
            if (adapter == null) {
                adapter = EpisodeDetailsAdapter {
                    val bundle = Bundle()
                    bundle.putInt(KEY_TO_CHARACTER_DETAILS, it)
                    val charactersDetailsFragment = CharactersDetailsFragment()
                    charactersDetailsFragment.arguments = bundle
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container, charactersDetailsFragment)
                        .addToBackStack("")
                        .commit()
                }
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
            (adapter as? EpisodeDetailsAdapter)?.setList(list)
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