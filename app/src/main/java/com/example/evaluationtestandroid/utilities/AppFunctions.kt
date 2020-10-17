package com.example.evaluationtestandroid.utilities

import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.evaluationtestandroid.R
import com.squareup.picasso.Picasso

fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = true) {
    if (addToBackStack) {
        APP_ACTIVITY.supportFragmentManager.beginTransaction()
            .replace(R.id.data_container, fragment).addToBackStack(null).commit()
        APP_ACTIVITY.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        APP_ACTIVITY.mToolbar.setNavigationOnClickListener {
            APP_ACTIVITY.supportFragmentManager.popBackStack()
        }
    } else {
        APP_ACTIVITY.supportFragmentManager.beginTransaction()
            .replace(R.id.data_container, fragment).commit()
    }
}

fun ImageView.downloadAndSetImage(url: String) {
    Picasso.get().load(url).fit()
        .placeholder(R.drawable.ic_base_image).into(this)
}

fun showToast(message: String) {
    Toast.makeText(APP_ACTIVITY, message, Toast.LENGTH_SHORT).show()
}