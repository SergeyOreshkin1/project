package com.example.finalproject.presentation.episodes

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.finalproject.R
import com.example.finalproject.data.db.episodes.EpisodesDatabase
import com.example.finalproject.databinding.FragmentEpisodesBinding
import com.example.finalproject.presentation.App
import com.example.finalproject.presentation.episodes.adapter.EpisodesAdapter
import com.example.finalproject.presentation.episodes.viewModel.EpisodesViewModel
import com.example.finalproject.presentation.episodes.viewModel.EpisodesViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodesFragment : Fragment() {

    lateinit var viewModel: EpisodesViewModel

    @Inject
    lateinit var episodesDatabase: EpisodesDatabase

    @Inject
    lateinit var viewModelFactory: EpisodesViewModelFactory

    private val adapter = EpisodesAdapter()
    private var _binding: FragmentEpisodesBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEpisodesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter
        (requireActivity().application as App).applicationComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[EpisodesViewModel::class.java]
        loadEpisodes()
        swipeRefreshLayoutSettings()
    }

    private fun loadEpisodes() {
        lifecycleScope.launch {
            viewModel.episodesList.collect {
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
