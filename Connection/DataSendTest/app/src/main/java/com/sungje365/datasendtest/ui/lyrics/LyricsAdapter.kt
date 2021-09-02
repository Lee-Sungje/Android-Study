package com.sungje365.datasendtest.ui.lyrics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sungje365.datasendtest.R
import com.sungje365.datasendtest.data.Lyrics

class LyricsAdapter(private val lyrics: MutableList<Lyrics>) : RecyclerView.Adapter<LyricsAdapter.LyricsViewHolder>() {
    private var mOnItemClickListener: OnItemClickListener? = null
    private var mOnItemHighlightListener: OnItemHighlightListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LyricsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lyrics, parent, false)

        return LyricsViewHolder(view)
    }

    override fun onBindViewHolder(holder: LyricsViewHolder, position: Int) {
        holder.bind(lyrics[position])
    }

    override fun getItemCount(): Int = lyrics.size

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.mOnItemClickListener = listener
    }

    fun setOnItemHighlightListener(listener: OnItemHighlightListener) {
        this.mOnItemHighlightListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    interface OnItemHighlightListener {
        fun onItemHighlightListener(view: View, position: Int)
    }

    inner class LyricsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvItemContent: TextView = itemView.findViewById(R.id.tv_item_lyrics)

        init {
            itemView.setOnClickListener {
                val position: Int = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    mOnItemClickListener?.apply { onItemClick(it, position) }
                    mOnItemHighlightListener?.apply { onItemHighlightListener(it, position) }
                }
            }
        }

        fun bind(item: Lyrics) {
            tvItemContent.text = item.content
        }
    }
}