package com.sungje365.musicplayer.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.sungje365.musicplayer.data.model.Lyrics
import com.sungje365.musicplayer.data.model.Music
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun URL.toMusic() = JSONObject(readText()).run {
    Music(
        this.getString("singer"),
        this.getString("album"),
        this.getString("title"),
        this.getInt("duration"),
        this.getString("image"),
        this.getString("file"),
        this.getString("lyrics")
    )
}

fun ImageView.loadImage(imageUrl: String) {
    Glide.with(this)
        .load(imageUrl)
        .override(this.width, this.height)
        .into(this)
}

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