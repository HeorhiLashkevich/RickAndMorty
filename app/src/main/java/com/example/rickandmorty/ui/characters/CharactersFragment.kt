package com.example.rickandmorty.ui.characters

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
import com.example.rickandmort.databinding.FragmentCharactersBinding
import com.example.rickandmorty.KEY_FROM_EPISODE_TO_CHARACTER
import com.example.rickandmorty.api.CharactersResult
import com.example.rickandmorty.ui.RecyclerMargin
import com.example.rickandmorty.ui.characterdetails.CharactersDetailsFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class CharactersFragment : Fragment() {

    private lateinit var binding: FragmentCharactersBinding
    private val viewModel by viewModels<CharactersViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
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


    private suspend fun initAdapter(list: PagingData<CharactersResult>) {
        binding.recyclerCharacters.run {
            val charactersDetailsFragment = CharactersDetailsFragment()
            addItemDecoration()
            if (adapter == null) {
                adapter = CharactersPagingAdapter {
                    val bundle = Bundle()
                    bundle.putInt(KEY_FROM_EPISODE_TO_CHARACTER, it)
                    charactersDetailsFragment.arguments = bundle
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container, charactersDetailsFragment)
                        .addToBackStack("")
                        .commit()

                }
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
            (adapter as? CharactersPagingAdapter)?.submitData(list)
        }

//        binding.swipeCharacters.setOnRefreshListener {
//            CharactersPagingAdapter().refresh()
//        }
    }

    private fun addItemDecoration() {
        val itemMargin = RecyclerMargin()
        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
//        setProgressBarAccordingToLoadState()
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.divider_drawable))
        binding.recyclerCharacters.run {
            addItemDecoration(dividerItemDecoration)
            addItemDecoration(itemMargin)
        }
    }

//    private fun setProgressBarAccordingToLoadState() {
//        lifecycleScope.launch {
//            CharactersPagingAdapter().loadStateFlow.collectLatest {
//                binding.run {
//                    determinateBar.isVisible = it.append is LoadState.Loading
//                }
//
//            }
//        }
//    }
}

