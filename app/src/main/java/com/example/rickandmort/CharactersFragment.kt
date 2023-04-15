package com.example.rickandmort

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmort.api.CharactersResult
import com.example.rickandmort.databinding.FragmentCharactersBinding


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
        viewModel.listCharacters.observe(viewLifecycleOwner) {
            setList(it)
        }
        viewModel.getAllCharacters()

    }

    private fun setList(list: ArrayList<CharactersResult>) {
        binding.recyclerCharacters.run {

            if (adapter == null) {
                adapter = CharactersAdapter()
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            }
            (adapter as? CharactersAdapter)?.setList(list as ArrayList<CharactersResult>)
        }
    }

}