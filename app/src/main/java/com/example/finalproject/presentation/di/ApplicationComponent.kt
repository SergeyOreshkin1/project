package com.example.finalproject.presentation.di

import android.content.Context
import com.example.finalproject.presentation.characters.CharactersFragment
import com.example.finalproject.presentation.episodes.EpisodesFragment
import com.example.finalproject.presentation.locations.LocationsFragment
import com.example.finalproject.presentation.root.RootFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, UseCaseModule::class, RepositoryModule::class, DatabaseModule::class])
interface ApplicationComponent {

    fun inject(fragment: RootFragment)

    fun inject(fragment: CharactersFragment)

    fun inject(fragment: LocationsFragment)

    fun inject(fragment: EpisodesFragment)

   // fun inject(presenter: CharactersPresenter)

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context) : ApplicationComponent
    }
}
