package com.sungje365.datasendtest.ui.main

import android.media.MediaPlayer
import android.util.Log
import androidx.lifecycle.*
import com.sungje365.datasendtest.api.RetrofitService
import com.sungje365.datasendtest.data.LyricsManager
import com.sungje365.datasendtest.data.Music
import com.sungje365.datasendtest.util.loadMedia
import com.sungje365.datasendtest.util.toLyrics
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _musicInfo = MutableLiveData<Music>()
    val musicInfo: LiveData<Music>
        get() = _musicInfo

    private val _player = MutableLiveData<MediaPlayer>()
    val player: LiveData<MediaPlayer>
        get() = _player

    fun communicateNetwork() = viewModelScope.launch(Dispatchers.IO) {
        val call = RetrofitService.getInstance().getMusic()
        call.enqueue(object : Callback<Music> {
            override fun onResponse(call: Call<Music>, response: Response<Music>) {
                if (response.isSuccessful) {
                    response.body()?.let { info ->
                        val lyricsManager = LyricsManager.getInstance()

                        info.lyrics.split('\n').forEach {
                            lyricsManager.add(it.toLyrics())
                        }
                        _musicInfo.value = info
                        _player.value = MediaPlayer().loadMedia(info.file)

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

    fun playMusic() {
        _player.value?.let {
            when(it.isPlaying) {
                true -> onPause()
                false -> onStart()
            }
        }
    }

    private fun onStart() {
        _player.value?.start()
    }
    private fun onPause() {
        _player.value?.pause()
    }
}