package com.example.rickandmorty.present.episodesdetails

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmort.R
import com.example.rickandmort.databinding.FragmentEpisodeDetailsBinding
import com.example.rickandmorty.App
import com.example.rickandmorty.utils.CHARACTER_DETAILS_TAG
import com.example.rickandmorty.utils.KEY_TO_CHARACTER_DETAILS
import com.example.rickandmorty.utils.KEY_TO_EPISODE_DETAILS
import com.example.rickandmorty.data.remove.service.model.CharactersResult
import com.example.rickandmorty.utils.RecyclerMargin
import com.example.rickandmorty.present.characterdetails.CharactersDetailsFragment
import javax.inject.Inject


class EpisodeDetailsFragment : Fragment() {
    private lateinit var binding: FragmentEpisodeDetailsBinding
    private var episodeId = 0

    @Inject
    lateinit var viewModelProvider: EpisodeDetailsModelProvider
    private lateinit var viewModel: EpisodeDetailsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.appComponent.inject(this)
        viewModel =
            ViewModelProvider(this, viewModelProvider).get(EpisodeDetailsViewModel::class.java)
    }

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
        binding.episodeDetailsBackButton.setOnClickListener {
          parentFragmentManager.popBackStack()
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
                    val charactersDetailsFragment =
                        CharactersDetailsFragment()
                    charactersDetailsFragment.arguments = bundle
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container, charactersDetailsFragment)
                        .addToBackStack(CHARACTER_DETAILS_TAG)
                        .commit()
                }
                layoutManager =
                    GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL,    false)
            }
            (adapter as? EpisodeDetailsAdapter)?.setList(list)
        }

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun addItemDecoration() {
        val itemMargin = RecyclerMargin()
        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        val dividerItemDecoration2 = DividerItemDecoration(context, RecyclerView.HORIZONTAL)
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.divider_drawable))
        binding.recyclerEpisode.run {
            addItemDecoration(dividerItemDecoration)
            addItemDecoration(dividerItemDecoration2)
            addItemDecoration(itemMargin)
        }
    }


}