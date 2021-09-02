package com.sungje365.datasendtest.ui.main

import android.os.Bundle
import android.widget.SeekBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.sungje365.datasendtest.R
import com.sungje365.datasendtest.data.LyricsManager
import com.sungje365.datasendtest.databinding.ActivityMainBinding
import com.sungje365.datasendtest.ui.lyrics.LyricsAdapter
import com.sungje365.datasendtest.util.toTime
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var lyricsManager: LyricsManager
    private lateinit var lyricsAdapter: LyricsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        lyricsManager = LyricsManager.getInstance()

        viewModel.player.observe(this, Observer { player ->
            binding.sbMainDuration.max = player.duration.div(1000)
            binding.tvMainDuration.text = player.duration.toLong().toTime()
        })

        viewModel.communicateNetwork()
        binding.apply {
            mainViewModel = viewModel
            lifecycleOwner = this@MainActivity
            sbMainDuration.setOnSeekBarChangeListener(this@MainActivity)
        }

        updateTime().start()
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if (fromUser) {
            seekBar?.progress = progress
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        seekBar?.let {
            viewModel.player.value?.pause()
        }
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        seekBar?.let {
            viewModel.player.value?.seekTo(it.progress * 1000)
        }
    }

//    private fun loadRecyclerView() {
//        lyricsAdapter = LyricsAdapter(lyricsManager.getLyrics())
//        lyricsAdapter.apply {
//            setOnItemClickListener(object : LyricsAdapter.OnItemClickListener {
//                override fun onItemClick(view: View, position: Int) {
//                    Snackbar.make(
//                        view,
//                        "duration: ${lyricsManager.get(position)?.duration}",
//                        Snackbar.LENGTH_SHORT
//                    ).show()
//                }
//            })
//            setOnItemHighlightListener(object : LyricsAdapter.OnItemHighlightListener {
//                override fun onItemHighlightListener(view: View, position: Int) {
//                    TODO("Not yet implemented")
//                }
//            })
//            notifyDataSetChanged()
//        }
//
//        findViewById<RecyclerView>(R.id.rv_lyrics).adapter = lyricsAdapter
//    }

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