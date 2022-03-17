package com.egoriku.ladyhappy

import android.app.Application
import com.egoriku.root.koin.initKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }
}