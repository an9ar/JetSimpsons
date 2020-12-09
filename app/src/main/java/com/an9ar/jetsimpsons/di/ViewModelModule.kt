package com.an9ar.jetsimpsons.di

import com.an9ar.jetsimpsons.repositories.SimpsonsRepository
import com.an9ar.jetsimpsons.viewmodels.EpisodesListViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun provideEpisodesListViewModel(
            simpsonsRepository: SimpsonsRepository
    ) = EpisodesListViewModel(
            simpsonsRepository = simpsonsRepository
    )

}