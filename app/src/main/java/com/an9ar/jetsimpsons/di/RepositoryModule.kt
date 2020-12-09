package com.an9ar.jetsimpsons.di

import com.an9ar.jetsimpsons.repositories.SimpsonsRepository
import com.an9ar.jetsimpsons.repositories.SimpsonsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
interface RepositoryModule {
    @Binds
    fun provideSimpsonsRepository(impl: SimpsonsRepositoryImpl): SimpsonsRepository
}