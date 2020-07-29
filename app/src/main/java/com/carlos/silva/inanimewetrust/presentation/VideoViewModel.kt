package com.carlos.silva.inanimewetrust.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.carlos.silva.core.Utils.KEY_WARD_SD
import com.carlos.silva.core.domain.Video
import com.carlos.silva.inanimewetrust.framework.InAnimeWeTrustViewModel
import com.carlos.silva.inanimewetrust.framework.Interactors
import com.carlos.silva.inanimewetrust.presentation.anime.AnimeItem
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class VideoViewModel(
    application: Application,
    private val interactors: Interactors
) : InAnimeWeTrustViewModel(application, interactors) {
    val playSDLiveData = MutableLiveData<Video>()
    val playHDLiveData = MutableLiveData<Video>()
    val currentVideoLiveData = MutableLiveData<Video>()

    fun getEpisode(animeItem: AnimeItem) {
        viewModelScope.launch {
            val videos = async { interactors.loadEpisode(animeItem.episode?.link) }.await()
            videos?.forEach {
                if(it.label.toUpperCase().contains(KEY_WARD_SD)) {
                    playSDLiveData.value = it
                }else {
                    playHDLiveData.value = it
                }
            }
        }
    }

    fun playHD() = playHDLiveData.value.let {
        currentVideoLiveData.value = it
    }

    fun playSD() = playSDLiveData.value.let {
        currentVideoLiveData.value = it
    }
}