package com.canerture.booksapp.common

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun setViewsVisible(vararg views: View) {
    views.forEach { it.visible() }
}

fun setViewsGone(vararg views: View) {
    views.forEach { it.gone() }
}

fun View.showSnackbar(text: String) {
    Snackbar.make(this, text, 1000).show()
}

fun hideKeyboard(activity: Activity, view: View) {
    val inputMethodManager =
        activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}