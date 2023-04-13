package com.example.rickandmort

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmort.api.EpisodeResult
import com.example.rickandmort.databinding.FragmentEpisodesBinding


class EpisodesFragment : Fragment() {

    private lateinit var binding: FragmentEpisodesBinding

    private val viewModel by viewModels<EpisodesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEpisodesBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.listEpisodes.observe(viewLifecycleOwner) {
            setList(it)
        }
        viewModel.getAllEpisodes()
    }

    private fun setList(list: List<EpisodeResult>) {
        binding.recyclerEpisodes.run {

            if (adapter == null) {
                adapter = EpisodesAdapter()
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
            (adapter as? EpisodesAdapter)?.setList(list as ArrayList<EpisodeResult>)
        }


    }
}
