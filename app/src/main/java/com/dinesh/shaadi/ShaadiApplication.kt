package com.dinesh.shaadi

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ShaadiApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}