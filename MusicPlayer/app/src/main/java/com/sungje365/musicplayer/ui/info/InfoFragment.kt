package com.sungje365.musicplayer.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sungje365.musicplayer.R
import com.sungje365.musicplayer.data.model.LyricsManager
import com.sungje365.musicplayer.databinding.FragmentInfoBinding
import com.sungje365.musicplayer.ui.main.MainViewModel
import com.sungje365.musicplayer.util.loadImage
import com.sungje365.musicplayer.util.toLyrics

class InfoFragment : Fragment() {
    private lateinit var binding: FragmentInfoBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false)
        val lyricsManager = LyricsManager.getInstance()

        viewModel.musicInfo.observe(viewLifecycleOwner, Observer { info ->
            info.lyrics.split('\n').forEach {
                lyricsManager.add(it.toLyrics())
            }
            binding.apply {
                tvInfoTitle.text = info.title
                tvInfoAlbum.text = info.album
                tvInfoSinger.text = info.singer
                ivInfoCover.loadImage(info.image)
//                rvInfoLyrics.apply {
//                    adapter = LyricsAdapter(lyricsManager.getLyrics())
//                    layoutManager = LinearLayoutManager(this@InfoFragment.context)
//                }
            }
        })

        binding.apply {
            lifecycleOwner = this@InfoFragment
            sharedViewModel = viewModel
        }

        return binding.root
    }
}