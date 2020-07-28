package com.carlos.silva.inanimewetrust.presentation.present

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.carlos.silva.inanimewetrust.R
import kotlinx.android.synthetic.main.viewpager_episode_item.*

class PresentFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.viewpager_episode_item,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (arguments?.getSerializable(EXTRAS) as com.carlos.silva.core.domain.Episode?)?.let {
            Glide.with(requireContext())
                .load(it.imagePath)
                .into(image)

            text1.text = it.title
        }

    }

    companion object {
        fun newInstance(episode: com.carlos.silva.core.domain.Episode) = PresentFragment()
            .apply {
            arguments = Bundle().apply {
                putSerializable(EXTRAS, episode)
            }
        }

        const val EXTRAS = "episode_data"
    }
}