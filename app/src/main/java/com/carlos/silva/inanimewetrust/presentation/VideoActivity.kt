package com.carlos.silva.inanimewetrust.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.carlos.silva.inanimewetrust.R
import com.carlos.silva.inanimewetrust.presentation.anime.AnimeItem

import kotlinx.android.synthetic.main.activity_video.*


class VideoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

            video.settings.javaScriptEnabled = true

        loadVideo()
    }

    private fun loadVideo() {
        (intent.extras?.getSerializable(AnimeItem.EXTRAS) as AnimeItem?)?.let {
            video.loadUrl(it.episode?.link)
        }
    }

}