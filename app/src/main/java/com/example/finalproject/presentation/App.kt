package com.example.finalproject.presentation

import android.app.Application
import com.example.finalproject.presentation.di.ApplicationComponent
import com.example.finalproject.presentation.di.DaggerApplicationComponent

class App : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}
