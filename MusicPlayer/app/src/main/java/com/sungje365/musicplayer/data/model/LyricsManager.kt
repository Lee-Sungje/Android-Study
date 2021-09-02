package com.sungje365.musicplayer.data.model

class LyricsManager private constructor() {

    companion object {
        @Volatile
        private var instance: LyricsManager? = null

        @JvmStatic
        fun getInstance(): LyricsManager =
            instance ?: synchronized(this) {
                instance ?: LyricsManager().also {
                    instance = it
                }
            }
    }

    private val lyrics = mutableListOf<Lyrics>()

    fun add(lyric: Lyrics) = lyrics.add(lyric)

    fun clear() = lyrics.clear()

    fun get(index: Int) = if (isRange(index)) lyrics[index] else null

    fun getLyrics() = lyrics

    fun find(duration: Long): Int {
        var low = 0
        var high = lyrics.lastIndex

        while (low <= high) {
            val mid = (low + high) / 2

            if (duration < lyrics[mid].duration) {
                high = mid - 1
            } else {
                low = mid + 1
            }
        }
        return low - 1
    }

    private fun isRange(index: Int) = index in 0..lyrics.lastIndex
}