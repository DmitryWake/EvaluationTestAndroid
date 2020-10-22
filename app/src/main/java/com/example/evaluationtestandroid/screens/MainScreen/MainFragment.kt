package com.example.evaluationtestandroid.screens.MainScreen

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
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var mAdapter: MainAdapter

    private var search: String = ""

    private lateinit var mSearchEditText: EditText
    private lateinit var mSearchEraseButton: ImageView

    companion object {
        var mRecyclerViewPosition: Int = 0

        private var dataList = mutableListOf<AlbumModel>()
    }

    override fun onStart() {
        super.onStart()

        initFields()
        initFunctions()
        initRecyclerView()

        if (dataList.isEmpty())
            updateData()

        mRecyclerView.scrollToPosition(mRecyclerViewPosition)
    }

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.mToolbar.search_toolbar.visibility = View.VISIBLE
        APP_ACTIVITY.supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onPause() {
        super.onPause()
        // Safe current position
        mRecyclerViewPosition = mLayoutManager.findFirstVisibleItemPosition()
    }

    // add some listeners
    private fun initFunctions() {
        mSearchEditText.addTextChangedListener(
            AppTextWatcher {
                search = mSearchEditText.text.toString()
                if (search.isNotEmpty()) {
                    mSearchEraseButton.visibility = View.VISIBLE
                    updateData()
                } else {
                    mSearchEraseButton.visibility = View.INVISIBLE
                }
            })
        mSearchEraseButton.setOnClickListener {
            mSearchEditText.text = null
            mSearchEraseButton.visibility = View.INVISIBLE
        }
    }

    // initialization
    private fun initFields() {
        mLayoutManager = LinearLayoutManager(this.context)
        mSearchEditText = APP_ACTIVITY.mToolbar.search_toolbar.album_name_edit_text
        mSearchEraseButton = APP_ACTIVITY.mToolbar.search_toolbar.clear_icon
        if (mSearchEditText.text.isEmpty()) {
            mSearchEraseButton.visibility = View.INVISIBLE
        } else {
            mSearchEraseButton.visibility = View.VISIBLE
        }
    }

    private fun initRecyclerView() {
        mRecyclerView = albums_recycler_view

        mAdapter = MainAdapter(dataList)

        mRecyclerView.isNestedScrollingEnabled = false
        mRecyclerView.layoutManager = mLayoutManager
        mRecyclerView.adapter = mAdapter
    }

    private fun updateData() {
        getAlbums(search) { results ->
            dataList = results
            dataList.sortBy { it.name }
            mAdapter.changeData(results)
        }
    }
}