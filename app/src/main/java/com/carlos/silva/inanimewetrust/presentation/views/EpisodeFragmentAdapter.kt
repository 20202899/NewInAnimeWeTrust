package com.carlos.silva.inanimewetrust.presentation.views

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.carlos.silva.core.domain.Episode
import com.carlos.silva.inanimewetrust.presentation.present.PresentFragment

class EpisodeFragmentAdapter (
    private val context: AppCompatActivity,
    private val episodes: MutableList<Episode>
) : FragmentStateAdapter(context) {
    override fun getItemCount() = episodes.size

    override fun createFragment(position: Int) = PresentFragment.newInstance(episodes[position])

}