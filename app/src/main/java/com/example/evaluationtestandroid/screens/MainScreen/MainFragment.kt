package com.example.evaluationtestandroid.screens.MainScreen

import android.util.Log
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.evaluationtestandroid.R
import com.example.evaluationtestandroid.iTunesSearchAPI.getAlbums
import com.example.evaluationtestandroid.models.AlbumModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var mRecyclerView: RecyclerView
    private val dataList = mutableListOf<AlbumModel>()
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var mAdapter: MainAdapter

    var isScrolling: Boolean = false

    private var count: Int = 0

    override fun onResume() {
        super.onResume()
        initFields()
        initRecyclerView()
        updateData()
    }

    private fun initFields() {
        mLayoutManager = LinearLayoutManager(this.context)
    }

    private fun initRecyclerView() {
        mRecyclerView = albums_recycler_view

        mAdapter = MainAdapter(dataList)

        mRecyclerView.isNestedScrollingEnabled = false
        mRecyclerView.layoutManager = mLayoutManager
        mRecyclerView.adapter = mAdapter

        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                    isScrolling = true
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                Log.w("Scrolled","${mLayoutManager.findFirstVisibleItemPosition()}")
                Log.w("Scrolled","size ${mAdapter.itemCount}")
                if (isScrolling && dy > 0 && mLayoutManager.findFirstVisibleItemPosition() >= count - 10) {
                    updateData()
                }
            }
        })
    }

    fun updateData() {
        isScrolling = false
        count += 20
        getAlbums("Origin", mAdapter, count)
    }
}