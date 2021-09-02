package com.sungje365.musicplayer.ui.main

import android.os.Bundle
import android.widget.SeekBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.sungje365.musicplayer.R
import com.sungje365.musicplayer.databinding.ActivityMainBinding
import com.sungje365.musicplayer.util.toMusic
import com.sungje365.musicplayer.util.toTime
import kotlinx.coroutines.*
import java.net.URL

class MainActivity : AppCompatActivity() {
    private val musicUrl =
        "https://grepp-programmers-challenges.s3.ap-northeast-2.amazonaws.com/2020-flo/song.json"

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private var isDisplayLyrics = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        lifecycleScope.launch(Dispatchers.IO) {
            val musicInfo = URL(musicUrl).toMusic()
            withContext(Dispatchers.Main) {
                viewModel.sendMusicInfo(musicInfo)
                viewModel.player.value?.let {
                    binding.sbMainDuration.max = it.duration.div(1000)
                    binding.tvMainDuration.text = it.duration.toLong().toTime()
                }
            }
        }
        binding.apply {
            lifecycleOwner = this@MainActivity
            mainViewModel = viewModel

            sbMainDuration.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if (fromUser) {
                        seekBar?.progress = progress
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    seekBar?.let {
                        viewModel.player.value?.apply {
                            seekTo(it.progress * 1000)
                        }
                    }
                }
            })

            ibMainLyrics.setOnClickListener {
                isDisplayLyrics = isDisplayLyrics.not()
                when (isDisplayLyrics) {
                    false -> fragMain.findNavController().navigate(R.id.action_lyrics_to_info)
                    true -> fragMain.findNavController().navigate(R.id.action_info_to_lyrics)
                }
            }
        }

        updateTime().start()
    }

    override fun onDestroy() {
        super.onDestroy()
        updateTime().cancel()
    }



    private fun updateTime() = CoroutineScope(Dispatchers.Default).launch {
        while (true) {
            viewModel.player.value?.let { player ->
                withContext(Dispatchers.Main) {
                    binding.tvMainTime.text = player.currentPosition.toLong().toTime()
                    binding.sbMainDuration.progress = player.currentPosition.div(1000)
                }
            }
            delay(200L)
        }
    }
}