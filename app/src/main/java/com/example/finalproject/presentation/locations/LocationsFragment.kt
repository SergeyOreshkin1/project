package com.example.finalproject.presentation.locations

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
import com.example.finalproject.data.db.locations.LocationsDatabase
import com.example.finalproject.databinding.FragmentLocationsBinding
import com.example.finalproject.presentation.App
import com.example.finalproject.presentation.locations.adapter.LocationsAdapter
import com.example.finalproject.presentation.locations.viewModel.LocationsViewModel
import com.example.finalproject.presentation.locations.viewModel.LocationsViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationsFragment : Fragment() {

    lateinit var viewModel: LocationsViewModel

    @Inject
    lateinit var locationsDatabase: LocationsDatabase

    @Inject
    lateinit var viewModelFactory: LocationsViewModelFactory

    private val adapter = LocationsAdapter()
    private var _binding: FragmentLocationsBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter
        (requireActivity().application as App).applicationComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[LocationsViewModel::class.java]
        loadLocations()
        swipeRefreshLayoutSettings()
    }

    private fun loadLocations() {
        lifecycleScope.launch {
            viewModel.locationsList.collect {
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
