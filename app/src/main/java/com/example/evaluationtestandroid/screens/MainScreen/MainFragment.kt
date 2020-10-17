package com.example.evaluationtestandroid.screens.MainScreen

import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.evaluationtestandroid.R
import com.example.evaluationtestandroid.iTunesSearchAPI.getAlbums
import com.example.evaluationtestandroid.models.AlbumModel
import com.example.evaluationtestandroid.utilities.APP_ACTIVITY
import com.example.evaluationtestandroid.utilities.AppTextWatcher
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.toolbar_search.view.*

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var mRecyclerView: RecyclerView
    private val dataList = mutableListOf<AlbumModel>()
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var mAdapter: MainAdapter

    var isScrolling: Boolean = false

    private var count: Int = 0

    private var search: String = ""

    private lateinit var mSearchEditText: EditText
    private lateinit var mSearchEraseButton: ImageView

    override fun onResume() {
        super.onResume()
        initFields()
        initFunctions()
        initRecyclerView()
        updateData()
    }

    private fun initFunctions() {
        mSearchEditText.addTextChangedListener(
            AppTextWatcher {
                search = it.toString()
                if (search.isNotEmpty()) {
                    mSearchEraseButton.visibility = View.VISIBLE
                    count = 0
                    updateData()
                }
            })
        mSearchEraseButton.setOnClickListener {
            mSearchEditText.text = null
            mSearchEraseButton.visibility = View.GONE
        }
    }

    private fun initFields() {
        mLayoutManager = LinearLayoutManager(this.context)
        mSearchEditText = APP_ACTIVITY.mToolbar.search_toolbar.album_name_edit_text
        mSearchEraseButton = APP_ACTIVITY.mToolbar.search_toolbar.clear_icon
        mSearchEraseButton.visibility = View.GONE
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
                if (isScrolling && dy > 0 && mLayoutManager.findFirstVisibleItemPosition() >= count - 12) {
                    updateData()
                }
            }
        })
    }

    fun updateData() {
        isScrolling = false
        count += 20
        getAlbums(search, mAdapter, count)
    }
}