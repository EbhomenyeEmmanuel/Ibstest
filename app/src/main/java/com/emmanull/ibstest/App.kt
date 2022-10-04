package com.emmanull.ibstest

import android.app.Application
import com.emmanull.ibstest.data.di.ServiceLocator

class App : Application() {
    companion object {
        lateinit var serviceLocator: ServiceLocator
    }

    override fun onCreate() {
        super.onCreate()
        serviceLocator = ServiceLocator(this)
    }
}