package com.example.evaluationtestandroid.screens.MainScreen

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.evaluationtestandroid.R
import com.example.evaluationtestandroid.models.AlbumModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: MainAdapter

    override fun onResume() {
        super.onResume()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        mRecyclerView = albums_recycler_view

        val dataList = mutableListOf<AlbumModel>()
        repeat(3) {
            dataList.add(AlbumModel("Test$it", "Test$it", "Test$it"))
        }

        mAdapter = MainAdapter(dataList)

        mRecyclerView.layoutManager = LinearLayoutManager(this.context)
        mRecyclerView.adapter = mAdapter
    }

}