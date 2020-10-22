package com.example.evaluationtestandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.evaluationtestandroid.databinding.ActivityMainBinding
import com.example.evaluationtestandroid.screens.MainScreen.MainFragment
import com.example.evaluationtestandroid.utilities.APP_ACTIVITY
import com.example.evaluationtestandroid.utilities.replaceFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initFields()
        initFunctions()
    }

    private fun initFunctions() {
        setSupportActionBar(mToolbar)
        replaceFragment(MainFragment(), false)
    }

    private fun initFields() {
        APP_ACTIVITY = this
        mToolbar = mBinding.mainToolbar
    }
}