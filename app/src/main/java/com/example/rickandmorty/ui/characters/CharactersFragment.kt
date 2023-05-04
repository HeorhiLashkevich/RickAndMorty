package com.example.rickandmorty.ui.characters

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmort.R
import com.example.rickandmort.databinding.FragmentCharactersBinding
import com.example.rickandmorty.App
import com.example.rickandmorty.KEY_TO_CHARACTER_DETAILS
import com.example.rickandmorty.api.CharactersResult
import com.example.rickandmorty.ui.RecyclerMargin
import com.example.rickandmorty.ui.characterdetails.CharactersDetailsFragment
import kotlinx.coroutines.ObsoleteCoroutinesApi
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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.flow.collectLatest {
                initAdapter(it)
            }
        }



//        binding.characterSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
//            androidx.appcompat.widget.SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                lifecycleScope.launch {
//                    filterFirsOfSecondName(newText)
//                }
//                return true
//            }
//
//        })
//    }
//
//    private suspend fun filterFirsOfSecondName(query: String?) {
//        if (query != null) {
//            viewModel.getBySearchedName(query)
////            initAdapter(viewModel.listOfCharacters.value as PagingData<CharactersResult>)
//
//        }
////        if (viewModel.listOfCharacters.value?.results!!.isNotEmpty()) {
////
////        }
//
    }


    private suspend fun initAdapter(list: PagingData<CharactersResult>) {
        binding.recyclerCharacters.run {
            val charactersDetailsFragment = CharactersDetailsFragment()
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
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
            (adapter as? CharactersPagingAdapter)?.submitData(list)
        }

//        binding.swipeCharacters.setOnRefreshListener {
//            CharactersPagingAdapter().refresh()
//        }
    }


    @SuppressLint("UseCompatLoadingForDrawables")
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


