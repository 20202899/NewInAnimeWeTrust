package com.carlos.silva.inanimewetrust.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.carlos.silva.core.domain.Anime
import com.carlos.silva.inanimewetrust.R
import com.carlos.silva.inanimewetrust.framework.InAnimeWeTrustViewModelFactory
import kotlinx.android.synthetic.main.anime_item.*
import kotlinx.android.synthetic.main.home_fragment.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var currentView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val tmpView = currentView
        if (tmpView != null) {
            return tmpView
        }

        val view = inflater.inflate(
            R.layout.home_fragment,
            container,
            false
        )

        currentView = view

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProviders.of(this, InAnimeWeTrustViewModelFactory)
            .get(HomeViewModel::class.java)

        homeViewModel.homeLiveData.observe(viewLifecycleOwner, Observer { list ->
            currentView?.findViewById<RecyclerView>(R.id.recyclerview)?.let {
                if (it.adapter == null) {
                    with(it) {
                        setHasFixedSize(true)
                        adapter = HomeAdapter(
                            list,
                            requireActivity() as AppCompatActivity, homeViewModel::selectAnime)
                    }
                }else {
                    (it.adapter as HomeAdapter).clickListener = {anime,  indexPath ->
                        homeViewModel.selectAnime(anime, indexPath)
                    }
                }
            }
        })

        homeViewModel.animeLiveData.observe(viewLifecycleOwner, Observer {
            openAnime(it)
        })
    }

    private fun openAnime(anime: Anime?) = anime?.let {
        findNavController().navigate(R.id.action_homeFragment_to_animeFragment, Bundle().apply {
            putSerializable(Anime.ANIME_EXTRA, it)
            homeViewModel.selectAnime(null, null)
        })
    }
}