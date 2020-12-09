package com.an9ar.jetsimpsons.di

import com.an9ar.jetsimpsons.App
import com.an9ar.jetsimpsons.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    RepositoryModule::class,
    ViewModelModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(application: App): Builder
    }

    fun inject(activity: MainActivity)
}