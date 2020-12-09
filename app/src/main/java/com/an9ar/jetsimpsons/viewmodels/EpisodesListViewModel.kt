package com.an9ar.jetsimpsons.viewmodels

import androidx.lifecycle.ViewModel
import com.an9ar.jetsimpsons.data.Episode
import com.an9ar.jetsimpsons.repositories.SimpsonsRepository
import javax.inject.Inject

class EpisodesListViewModel @Inject constructor(
        private val simpsonsRepository: SimpsonsRepository
): ViewModel() {

    fun getEpisodesList(): List<Episode> {
        return simpsonsRepository.getEpisodesList()
    }

    fun getEpisodeById(id: Long): Episode? {
        return simpsonsRepository.getEpisodeById(id = id)
    }

    suspend fun setEpisodesList(episodes: List<Episode>) {
        return simpsonsRepository.setEpisodesList(episodes = episodes)
    }

}