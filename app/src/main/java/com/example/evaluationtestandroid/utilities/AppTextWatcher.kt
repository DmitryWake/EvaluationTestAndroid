package com.example.evaluationtestandroid.utilities

import android.text.Editable
import android.text.TextWatcher


// use to do some things after text changes
class AppTextWatcher(val function: (p0: Editable?) -> Unit): TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(p0: Editable?) {
        function(p0)
    }

}