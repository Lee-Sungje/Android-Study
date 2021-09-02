package com.sungje365.musicplayer.ui.main

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sungje365.musicplayer.data.model.Music

class MainViewModel : ViewModel() {
    private val _musicInfo = MutableLiveData<Music>()
    val musicInfo: LiveData<Music>
        get() = _musicInfo

    private val _player = MutableLiveData<MediaPlayer>()
    val player: LiveData<MediaPlayer>
        get() = _player

    fun sendMusicInfo(info: Music) {
        _musicInfo.value = info
        _player.value = MediaPlayer().apply {
            setDataSource(info.file)
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
            prepare()
        }
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