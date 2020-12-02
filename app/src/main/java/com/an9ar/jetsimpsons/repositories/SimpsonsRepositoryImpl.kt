package com.an9ar.jetsimpsons.repositories

import com.an9ar.jetsimpsons.data.Episode
import javax.inject.Inject

class SimpsonsRepositoryImpl @Inject constructor() : SimpsonsRepository{

    private var episodesList: List<Episode> = emptyList()

    override suspend fun setEpisodesList(episodes: List<Episode>){
        episodesList = episodes
    }

    override fun getEpisodesList(): List<Episode> = episodesList

    override fun getEpisodeById(id: Long): Episode? = episodesList.find { episode -> episode.id == id }
}