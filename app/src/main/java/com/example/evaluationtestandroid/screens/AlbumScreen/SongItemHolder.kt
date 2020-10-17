package com.example.evaluationtestandroid.screens.AlbumScreen

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.evaluationtestandroid.models.SongModel
import kotlinx.android.synthetic.main.song_item.view.*
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.min

class SongItemHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val mSongPositionText: TextView = view.song_number
    private val mSongNameText: TextView = view.song_name
    private val mSongAuthorText: TextView = view.song_author
    private val mSongTimeText: TextView = view.song_time

    @SuppressLint("SetTextI18n")
    fun draw(song: SongModel) {
        mSongPositionText.text = song.trackNumber.toString()
        mSongNameText.text = song.name
        mSongAuthorText.text = song.author
        var minutes = (song.timeInMillis.toLong() / 1000 / 60).toString()
        if (minutes.length == 1)
            minutes = "0$minutes"
        var seconds = (song.timeInMillis.toLong() / 1000 % 60).toString()
        if (seconds.length == 1)
            seconds = "0$seconds"
        mSongTimeText.text = "$minutes:$seconds"
    }
}