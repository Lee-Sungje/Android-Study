package com.sungje365.datasendtest.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sungje365.datasendtest.R
import com.sungje365.datasendtest.data.LyricsManager
import com.sungje365.datasendtest.databinding.FragmentInfoBinding
import com.sungje365.datasendtest.ui.lyrics.LyricsAdapter
import com.sungje365.datasendtest.ui.main.MainViewModel
import com.sungje365.datasendtest.util.loadImage
import com.sungje365.datasendtest.util.toLyrics

class InfoFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentInfoBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false)
        val lyricsManager = LyricsManager.getInstance()

        viewModel.musicInfo.observe(viewLifecycleOwner, Observer { info ->
            binding.apply {
                tvInfoTitle.text = info.title
                tvInfoAlbum.text = info.album
                tvInfoSinger.text = info.singer
                ivInfoCover.loadImage(info.image)
                rvInfoLyrics.apply {
                    adapter = LyricsAdapter(lyricsManager.getLyrics()).apply {
//                        setOnItemClickListener(object : LyricsAdapter.OnItemClickListener {
//                            override fun onItemClick(view: View, position: Int) {
//                                Snackbar.make(
//                                    view,
//                                    "duration: ${lyricsManager.get(position)?.duration}",
//                                    Snackbar.LENGTH_SHORT
//                                ).show()
//                            }
//
//                        })
                        notifyDataSetChanged()
                    }
                    layoutManager = LinearLayoutManager(this@InfoFragment.context)
                }
            }
        })

        binding.lifecycleOwner = this

        return binding.root
    }
}