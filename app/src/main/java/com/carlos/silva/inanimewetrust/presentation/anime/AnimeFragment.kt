package com.carlos.silva.inanimewetrust.presentation.anime

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.carlos.silva.core.domain.Anime
import com.carlos.silva.inanimewetrust.R
import com.carlos.silva.inanimewetrust.framework.InAnimeWeTrustViewModelFactory
import com.carlos.silva.inanimewetrust.presentation.MainActivity
import com.carlos.silva.inanimewetrust.presentation.VideoActivity
import kotlinx.android.synthetic.main.anime_fragment.*

class AnimeFragment : Fragment() {

    private lateinit var animeViewModel: AnimeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.anime_fragment,
            container,
            false
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        animeViewModel = ViewModelProviders.of(this, InAnimeWeTrustViewModelFactory)
            .get(AnimeViewModel::class.java)

        loadArguments()

        animeViewModel.episodesLiveData.observe(viewLifecycleOwner, Observer {
            with(recyclerview) {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = AnimeAdapter(it, this@AnimeFragment::openVideo)
            }
        })

        animeViewModel.animeLiveData.observe(viewLifecycleOwner, Observer {
            animeViewModel.getEpisodes(it)
        })

    }

    private fun openVideo(animeItem: AnimeItem) {
        startActivity(Intent(requireActivity(), VideoActivity::class.java).apply {
            putExtra(AnimeItem.EXTRAS, animeItem)
        })
    }

    private fun loadArguments() {
        (arguments?.getSerializable(Anime.ANIME_EXTRA) as Anime?)?.let {
            (requireActivity() as MainActivity).title = it.title
            animeViewModel.selectAnime(it)
        }
    }


}