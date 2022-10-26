package com.test.mytask.neosoft.ext;

import android.view.View

fun View.makeVisible(shouldShow: Boolean){
    visibility = if(shouldShow) View.VISIBLE else View.GONE
}






