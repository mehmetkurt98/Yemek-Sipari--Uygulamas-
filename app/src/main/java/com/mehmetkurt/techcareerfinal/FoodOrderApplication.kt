package com.mehmetkurt.techcareerfinal

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FoodOrderApplication: Application() {
    companion object {
        private lateinit var instance: FoodOrderApplication
        fun getAppContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}
