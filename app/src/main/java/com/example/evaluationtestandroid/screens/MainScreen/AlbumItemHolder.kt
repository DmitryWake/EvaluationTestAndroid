package com.example.evaluationtestandroid.screens.MainScreen

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.evaluationtestandroid.models.AlbumModel
import kotlinx.android.synthetic.main.album_item.view.*

class AlbumItemHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val mAlbumName = view.album_name_text
    private val mAlbumAuthor = view.album_author_text
    private val mAlbumDescription = view.album_description_text

    fun draw(album: AlbumModel) {
        mAlbumName.text = album.name
        mAlbumAuthor.text = album.author
        mAlbumDescription.text = album.description
    }
}