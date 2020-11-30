package com.an9ar.jetsimpsons.di

import com.an9ar.jetsimpsons.repositories.SimpsonsRepository
import com.an9ar.jetsimpsons.repositories.SimpsonsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun provideSimpsonsRepository(impl: SimpsonsRepositoryImpl): SimpsonsRepository
}