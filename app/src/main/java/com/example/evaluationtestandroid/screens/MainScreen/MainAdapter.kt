package com.example.evaluationtestandroid.screens.MainScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.evaluationtestandroid.R
import com.example.evaluationtestandroid.models.AlbumModel

class MainAdapter(private var dataList: MutableList<AlbumModel>) : RecyclerView.Adapter<AlbumItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumItemHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.album_item, parent, false)
        return AlbumItemHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumItemHolder, position: Int) {
        holder.draw(dataList[position], position)
    }

    override fun getItemCount(): Int = dataList.size

    fun changeData(newList: MutableList<AlbumModel>) {
        dataList = newList
        notifyDataSetChanged()
    }
}