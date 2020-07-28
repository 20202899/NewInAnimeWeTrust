package com.carlos.silva.inanimewetrust.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel

open class InAnimeWeTrustViewModel(application: Application, interactors: Interactors): AndroidViewModel(application) {
    protected val application = application as InAnimeWeTrustApplication
}