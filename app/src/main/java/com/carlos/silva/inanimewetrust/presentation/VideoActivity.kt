package com.carlos.silva.inanimewetrust.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.carlos.silva.inanimewetrust.R
import com.carlos.silva.inanimewetrust.framework.InAnimeWeTrustViewModelFactory
import com.carlos.silva.inanimewetrust.presentation.anime.AnimeItem
import com.longtailvideo.jwplayer.media.playlists.PlaylistItem

import kotlinx.android.synthetic.main.activity_video.*


class VideoActivity : AppCompatActivity() {

    private lateinit var videoViewModel: VideoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        videoViewModel = ViewModelProviders.of(this, InAnimeWeTrustViewModelFactory)
            .get(VideoViewModel::class.java)

        videoViewModel.playSDLiveData.observe(this, Observer {
            play_sd.visibility = View.VISIBLE
        })

        videoViewModel.playHDLiveData.observe(this, Observer {
            play_hd.visibility = View.VISIBLE
        })

        videoViewModel.currentVideoLiveData.observe(this, Observer {
            play_sd.visibility = View.INVISIBLE
            play_hd.visibility = View.INVISIBLE
//            loadVideo()
        })

        play_sd.setOnClickListener { videoViewModel.playSD() }
        play_hd.setOnClickListener { videoViewModel.playHD() }

        loadIntent()
    }

    override fun onResume() {
        super.onResume()
        jwplayer.onResume()
    }

    override fun onStart() {
        super.onStart()
        jwplayer.onStart()
    }

    override fun onPause() {
        super.onPause()
        jwplayer.onPause()
    }

    override fun onStop() {
        super.onStop()
        jwplayer.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        jwplayer.onDestroy()
    }

    private fun loadVideo() {
        val pi = PlaylistItem.Builder()
            .file("https://www.anitube.site/aHR0cHM6Ly9lbWFncmVjZXJwZXJkZW5kb2dvcmR1cmEuYmxvZ3Nwb3QuY29tLzIwMjAvMDcvZjA0NGM0NzBiZmNjN2EwYi5odG1s/0/bg.mp4")
            .title("Boruto")
            .description("A video player testing video.")
            .build()

        jwplayer.load(pi)
    }

    private fun loadIntent() {
        (intent.extras?.getSerializable(AnimeItem.EXTRAS) as AnimeItem?)?.let {
            videoViewModel.getEpisode(it)
        }
    }

}