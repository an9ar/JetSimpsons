package com.an9ar.jetsimpsons

import android.app.Application
import com.an9ar.jetsimpsons.di.AppComponent
import com.an9ar.jetsimpsons.di.DaggerAppComponent

class App : Application() {
    companion object{
        lateinit var appComponent: AppComponent
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().application(this).build()
    }
}