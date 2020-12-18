package com.an9ar.jetsimpsons.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.an9ar.jetsimpsons.data.Episode
import com.an9ar.jetsimpsons.ui.ListType

class EpisodesViewModel @ViewModelInject constructor() : ViewModel() {

    private val _episodesList: MutableLiveData<List<Episode>> = MutableLiveData()
    val episodesList: LiveData<List<Episode>>
        get() = _episodesList

    private val _episodesListType: MutableLiveData<ListType> = MutableLiveData()
    val episodesListType: LiveData<ListType>
        get() = _episodesListType

    fun getEpisodeById(id: Long): Episode? = episodesList.value?.find { episode -> episode.id == id }

    suspend fun setEpisodesList(episodes: List<Episode>) {
        _episodesList.value = episodes
    }

    fun setEpisodesListType(type: ListType) {
        _episodesListType.value = type
    }

}