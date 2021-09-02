package com.sungje365.recyclerviewtest.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.sungje365.musicplayer.data.model.LyricsManager
import com.sungje365.recyclerviewtest.data.model.Music
import com.sungje365.recyclerviewtest.R
import com.sungje365.recyclerviewtest.api.RetrofitService
import com.sungje365.recyclerviewtest.databinding.ActivityMainBinding
import com.sungje365.recyclerviewtest.ui.lyrics.LyricsAdapter
import com.sungje365.recyclerviewtest.util.toLyrics
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var lyricsAdapter: LyricsAdapter
    private val lyricsManager: LyricsManager by lazy {
        LyricsManager.getInstance()
    }
    private lateinit var musicInfo: Music

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        fetchJson()
    }

    private fun loadRecyclerView() {
        lyricsAdapter = LyricsAdapter(lyricsManager.getLyrics())
        lyricsAdapter.apply {
            setOnItemClickListener(object : LyricsAdapter.OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    Snackbar.make(
                        view,
                        "duration: ${lyricsManager.get(position)?.duration}",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            })
//            setOnItemHighlightListener(object : LyricsAdapter.OnItemHighlightListener {
//                override fun onItemHighlightListener(view: View, position: Int) {
//                    TODO("Not yet implemented")
//                }
//            })
            notifyDataSetChanged()
        }

        binding.rvMainLyrics.adapter = lyricsAdapter
    }

    private fun fetchJson() {
        val call = RetrofitService.getInstance().getMusic()
        call.enqueue(object : Callback<Music> {
            override fun onResponse(call: Call<Music>, response: Response<Music>) {
                if (response.isSuccessful) {
                    response.body()?.let { info ->
                        musicInfo = info
                        musicInfo.lyrics.split('\n').forEach {
                            lyricsManager.add(it.toLyrics())
                        }
                        loadRecyclerView()
                    }
                } else {
                    Log.e("onResponse", "response code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Music>, t: Throwable) {
                Log.e("onFailure", "${t.message}")
            }
        })
    }
}