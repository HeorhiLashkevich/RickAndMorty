//package com.example.rickandmorty.present.characters.chechDb
//
//import android.content.Context
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.rickandmort.databinding.FragmentCharacterSearchBinding
//
//
//class CharactersSearchFragment() : Fragment() {
//
//    private lateinit var binding: FragmentCharacterSearchBinding
//
//
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = FragmentCharacterSearchBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//    }
//
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
////        lifecycleScope.launch {
////            CharactersDataBaseRepository.characterDao?.let { initAdapter(it.getAll()) }
////        }
//
//
//    }
//
//    private fun initAdapter(list: List<CharactersEntity>) {
//        binding.recyclerCharactersSearch.run {
//            if (adapter == null) {
//                adapter = CharSearchAdapter()
//                layoutManager =
//                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//            }
//            (adapter as? CharSearchAdapter)?.setList(list)
//        }
//
//    }
//}