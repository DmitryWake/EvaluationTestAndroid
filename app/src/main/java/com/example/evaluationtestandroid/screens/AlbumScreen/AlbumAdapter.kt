package com.example.evaluationtestandroid.screens.AlbumScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.evaluationtestandroid.R
import com.example.evaluationtestandroid.models.SongModel

class AlbumAdapter(private var dataList: MutableList<SongModel>) : RecyclerView.Adapter<SongItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongItemHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.song_item, parent, false)
        return SongItemHolder(view)
    }

    override fun onBindViewHolder(holder: SongItemHolder, position: Int) {
        holder.draw(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    fun changeData(newList: MutableList<SongModel>) {
        dataList = newList
        notifyDataSetChanged()
    }
}