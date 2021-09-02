package com.sungje365.seekbartest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import android.widget.SeekBar
import android.widget.TextView
import kotlin.properties.Delegates

class MusicPlayerActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        private const val TAG = "MusicPlayerActivity"
    }

    private val seekBar: SeekBar by lazy {
        findViewById(R.id.seekbar_musicplayer)
    }
    private val playTime: Chronometer by lazy {
        findViewById(R.id.chronometer_musicplayer)
    }
    private val playButton: Button by lazy {
        findViewById(R.id.button_musicplayer_play)
    }

    var isMusicPlay: Boolean = false
    var pauseOffset: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_musicplayer)

        seekBar.max = 50
        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // SeekBar 조작중 작동
                // 시간(progress)을 변경
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // SeekBar 조작 시작시 작동
                // 음악 재생을 멈춤
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // SeekBar 조작 종료시 작동
                // 음악 재생 상태로 복귀
            }
        })

        playButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v) {
            playButton -> {
                if (!isMusicPlay) {
                    playButton.text = getString(R.string.all_pause)
                    playTime.base = SystemClock.elapsedRealtime() - pauseOffset
                    playTime.start()
                    isMusicPlay = true
                } else {
                    playButton.text = getString(R.string.all_play)
                    playTime.stop()
                    pauseOffset = SystemClock.elapsedRealtime() - playTime.base
                    isMusicPlay = false
                }
            }
        }
    }

    fun playMusic(): Unit {

    }

    fun pauseMusic(): Unit {}

}
