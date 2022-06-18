package com.canerture.booksapp.common

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar

fun showSnackbar(view: View, text: Int) {
    Snackbar.make(view, text, 1000).show()
}

fun hideKeyboard(activity: Activity, view: View) {
    val inputMethodManager =
        activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}