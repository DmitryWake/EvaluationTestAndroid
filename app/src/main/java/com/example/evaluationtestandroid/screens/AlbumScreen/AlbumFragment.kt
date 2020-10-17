package com.example.evaluationtestandroid.screens.AlbumScreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.evaluationtestandroid.R
import com.example.evaluationtestandroid.iTunesSearchAPI.getSongs
import com.example.evaluationtestandroid.models.AlbumModel
import com.example.evaluationtestandroid.models.SongModel
import com.example.evaluationtestandroid.utilities.APP_ACTIVITY
import com.example.evaluationtestandroid.utilities.downloadAndSetImage
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_album.*

class AlbumFragment(private val album: AlbumModel) : Fragment(R.layout.fragment_album) {

    private var dataList = mutableListOf<SongModel>()

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: AlbumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        APP_ACTIVITY.mToolbar.search_toolbar.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        initFields()
        loadData()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        mRecyclerView = songs_recycler_view

        mAdapter = AlbumAdapter(dataList)

        mRecyclerView.layoutManager = LinearLayoutManager(this.context)
        mRecyclerView.adapter = mAdapter
    }

    private fun initFields() {
        album_name_full_text.text = album.name
        album_author_full_text.text = album.author
        album_description_full_text.text = album.description
        album_full_image.downloadAndSetImage(album.imageUrl)
    }

    private fun loadData() {
        getSongs(album) {
            dataList = it
            dataList.sortBy { song -> song.trackNumber }
            mAdapter.changeData(dataList)
        }
    }
}