package com.example.finalproject.presentation.characters

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.finalproject.R
import com.example.finalproject.data.db.characters.CharactersDatabase
import com.example.finalproject.databinding.FragmentCharactersBinding
import com.example.finalproject.presentation.App
import com.example.finalproject.presentation.characters.adapter.CharactersAdapter
import com.example.finalproject.presentation.characters.viewModel.CharactersViewModel
import com.example.finalproject.presentation.characters.viewModel.CharactersViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersFragment : Fragment() {

    lateinit var viewModel: CharactersViewModel

    @Inject
    lateinit var charactersDatabase: CharactersDatabase

    @Inject
    lateinit var viewModelFactory: CharactersViewModelFactory

    private val adapter = CharactersAdapter()
    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as App).applicationComponent.inject(this)
        binding.recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this, viewModelFactory)[CharactersViewModel::class.java]
        loadCharacters()
        swipeRefreshLayoutSettings()
    }

    private fun loadCharacters() {
        lifecycleScope.launch {
            viewModel.charactersList.collect {
                adapter.submitData(it)
            }
        }
    }

    private fun swipeRefreshLayoutSettings() {
        binding.apply {
            itemsSwipeToRefresh.setProgressBackgroundColorSchemeColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.cyan_color
                )
            )
            itemsSwipeToRefresh.setColorSchemeColors(Color.WHITE)

            itemsSwipeToRefresh.setOnRefreshListener {
                lifecycleScope.launch {
                    delay(1000)
                    adapter.refresh()
                    itemsSwipeToRefresh.isRefreshing = false
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
