package com.carlos.silva.inanimewetrust.presentation.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.carlos.silva.core.domain.Anime
import com.carlos.silva.inanimewetrust.framework.InAnimeWeTrustViewModel
import com.carlos.silva.inanimewetrust.framework.Interactors
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeViewModel(application: Application, interactors: Interactors) :
    InAnimeWeTrustViewModel(application, interactors) {
    val homeLiveData = MutableLiveData<MutableList<Any>>()
    val animeLiveData = MutableLiveData<Anime?>()

    init {
        viewModelScope.launch {
            homeLiveData.value = async { interactors.getHome() }.await()
        }
    }

    fun selectAnime(anime: Anime?, indexPath: HomeAdapter.IndexPath?) {
        animeLiveData.value = anime
    }
}