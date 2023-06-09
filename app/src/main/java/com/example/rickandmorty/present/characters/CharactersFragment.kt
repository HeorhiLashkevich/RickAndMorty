package com.example.rickandmorty.present.characters

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
import com.example.rickandmort.databinding.FragmentCharactersBinding
import com.example.rickandmorty.App
import com.example.rickandmorty.utils.KEY_TO_CHARACTER_DETAILS
import com.example.rickandmorty.data.model.CharactersEntity
import com.example.rickandmorty.utils.RecyclerMargin
import com.example.rickandmorty.present.characterdetails.CharactersDetailsFragment

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class CharactersFragment() : Fragment() {

    private lateinit var binding: FragmentCharactersBinding

    @Inject
    lateinit var viewModelProvider: CharactersModelProvider
    private lateinit var viewModel: CharactersViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelProvider).get(CharactersViewModel::class.java)
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//
//        lifecycleScope.launch {
//            viewModel.getCharactersByName().collectLatest {
//                initAdapter(it)
//            }
//        }
        lifecycleScope.launch {
            viewModel.getCharacters().collectLatest {
                initAdapter(it)
            }
        }
    }

//        binding.apply {
//            characterSearchButton.setOnClickListener {
//                val input = characterSearch.text.toString()
//                viewModel.setSearchTerm(input)
//            }
//        }

//        binding.characterSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                if (newText.isNotBlank()) {
//                    viewModel.setSearchTerm(newText)
//                }
//                return false
//            }
//        })
//    }


    private suspend fun initAdapter(list: PagingData<CharactersEntity>) {
        binding.recyclerCharacters.run {
            val charactersDetailsFragment =
                CharactersDetailsFragment()
            addItemDecoration()
            if (adapter == null) {
                adapter = CharactersPagingAdapter {
                    val bundle = Bundle()
                    bundle.putInt(KEY_TO_CHARACTER_DETAILS, it)
                    charactersDetailsFragment.arguments = bundle
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container, charactersDetailsFragment)
                        .addToBackStack("")
                        .commit()
                }

                layoutManager =
                    GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            }
            (adapter as? CharactersPagingAdapter)?.submitData(list)
        }

    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private fun addItemDecoration() {
        val itemMargin = RecyclerMargin()
        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        val dividerItemDecoration2 = DividerItemDecoration(context, RecyclerView.HORIZONTAL)
//        setProgressBarAccordingToLoadState()
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.divider_drawable))
        binding.recyclerCharacters.run {
            addItemDecoration(dividerItemDecoration)
            addItemDecoration(itemMargin)
            addItemDecoration(dividerItemDecoration2)
        }
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


