package com.sungje365.datasendtest.util

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.sungje365.datasendtest.data.Lyrics
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun String.toLyrics(): Lyrics = run {
    val (_, time, content) = this.split('[', ']')
    Lyrics(time.toMillis(), content)
}

fun String.toMillis(): Long = SimpleDateFormat("mm:ss:SSS", Locale.getDefault()).apply {
    timeZone = TimeZone.getTimeZone("UTC")
}.parse(this)?.time ?: -1L

fun Long.toTime(): String = String.format("%d:%02d",
    TimeUnit.MILLISECONDS.toMinutes(this),
    TimeUnit.MILLISECONDS.toSeconds(this) -
            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(this))
)

fun ImageView.loadImage(imageUrl: String) {
    Glide.with(this)
        .load(imageUrl)
        .override(this.width, this.height)
        .into(this)
}

fun MediaPlayer.loadMedia(mediaUrl: String): MediaPlayer = this.apply {
    setDataSource(mediaUrl)
    setAudioAttributes(
        AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build()
    )
    prepare()
}
