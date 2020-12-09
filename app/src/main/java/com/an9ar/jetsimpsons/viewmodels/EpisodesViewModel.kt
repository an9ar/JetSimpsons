package com.an9ar.jetsimpsons.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.an9ar.jetsimpsons.data.Episode
import com.an9ar.jetsimpsons.repositories.SimpsonsRepository

class EpisodesViewModel @ViewModelInject constructor(
        private val simpsonsRepository: SimpsonsRepository
): ViewModel() {

    val episodesList: MutableLiveData<List<Episode>> = MutableLiveData()

    fun getEpisodeById(id: Long): Episode? = episodesList.value?.find { episode -> episode.id == id }

    suspend fun setEpisodesList(episodes: List<Episode>) {
        episodesList.value = episodes
    }

}