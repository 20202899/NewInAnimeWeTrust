package com.carlos.silva.inanimewetrust.framework

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalStateException

object InAnimeWeTrustViewModelFactory : ViewModelProvider.Factory {

    lateinit var application: Application
    lateinit var interactors: Interactors

    fun inject(application: Application, interactors: Interactors) {
        this.application = application
        this.interactors = interactors
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (InAnimeWeTrustViewModel::class.java.isAssignableFrom(modelClass)) {
            return modelClass.getConstructor(Application::class.java, Interactors::class.java)
                .newInstance(application, interactors)
        }else{
            throw IllegalStateException("Error")
        }
    }

}