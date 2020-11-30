package com.an9ar.jetsimpsons.repositories

import com.an9ar.jetsimpsons.Episode

interface SimpsonsRepository {
    fun getEpisodesList(): List<Episode>
    fun getEpisodeById(id: Long): Episode?
    suspend fun setEpisodesList(episodes: List<Episode>)
}