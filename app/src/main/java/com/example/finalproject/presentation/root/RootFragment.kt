package com.example.finalproject.presentation.root

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.finalproject.R
import com.example.finalproject.databinding.FragmentRootBinding
import com.example.finalproject.presentation.MainActivity
import com.example.finalproject.presentation.characters.CharactersFragment
import com.example.finalproject.presentation.episodes.EpisodesFragment
import com.example.finalproject.presentation.locations.LocationsFragment


class RootFragment : Fragment() {

    private var _binding: FragmentRootBinding? = null
    private val binding get() = requireNotNull(_binding)

    companion object {
        fun newInstance() = RootFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRootBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onViewCreatedDefaults()
        bottomMenuNavigation()
    }

    private fun onViewCreatedDefaults() {
        (requireActivity() as MainActivity).navigateToFragment(
            R.id.rootFragmentContainerView,
            CharactersFragment()
        )
        bottomMenuItemEnable(charactersItem = false, episodesItem = true, locationsItem = true)
    }

    private fun bottomMenuNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.characters -> {
                    (requireActivity() as MainActivity).navigateToFragment(
                        R.id.rootFragmentContainerView,
                        CharactersFragment()
                    )
                    bottomMenuItemEnable(
                        charactersItem = false,
                        episodesItem = true,
                        locationsItem = true
                    )
                }
                R.id.episodes -> {
                    (requireActivity() as MainActivity).navigateToFragment(
                        R.id.rootFragmentContainerView,
                        EpisodesFragment()
                    )
                    bottomMenuItemEnable(
                        charactersItem = true,
                        episodesItem = false,
                        locationsItem = true
                    )
                }
                R.id.locations -> {
                    (requireActivity() as MainActivity).navigateToFragment(
                        R.id.rootFragmentContainerView,
                        LocationsFragment()
                    )
                    bottomMenuItemEnable(
                        charactersItem = true,
                        episodesItem = true,
                        locationsItem = false
                    )
                }
            }
            true
        }
    }

    private fun bottomMenuItemEnable(
        charactersItem: Boolean,
        episodesItem: Boolean,
        locationsItem: Boolean
    ) {
        val charactersMenuItem = binding.bottomNavigation.menu.findItem(R.id.characters)
        charactersMenuItem.isEnabled = charactersItem
        val episodesMenuItem = binding.bottomNavigation.menu.findItem(R.id.episodes)
        episodesMenuItem.isEnabled = episodesItem
        val locationsMenuItem = binding.bottomNavigation.menu.findItem(R.id.locations)
        locationsMenuItem.isEnabled = locationsItem
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
