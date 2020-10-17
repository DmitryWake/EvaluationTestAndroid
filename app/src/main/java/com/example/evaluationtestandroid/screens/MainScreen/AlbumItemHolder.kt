package com.example.evaluationtestandroid.screens.MainScreen

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.evaluationtestandroid.models.AlbumModel
import com.example.evaluationtestandroid.screens.AlbumScreen.AlbumFragment
import com.example.evaluationtestandroid.utilities.downloadAndSetImage
import com.example.evaluationtestandroid.utilities.replaceFragment
import com.example.evaluationtestandroid.utilities.showToast
import kotlinx.android.synthetic.main.album_item.view.*

class AlbumItemHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val mAlbumBox = view.album_container
    private val mAlbumName = view.album_name_text
    private val mAlbumAuthor = view.album_author_text
    private val mAlbumDescription = view.album_description_text
    private val mAlbumImage = view.album_image

    fun draw(album: AlbumModel, position: Int) {
        mAlbumName.text = album.name
        mAlbumAuthor.text = album.author
        mAlbumDescription.text = album.description
        mAlbumImage.downloadAndSetImage(album.imageUrl)
        mAlbumBox.setOnClickListener {
            MainFragment.mRecyclerViewPosition = position
            replaceFragment(AlbumFragment(album), true)
        }
    }
}