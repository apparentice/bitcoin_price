package com.alokomkar.btc.extension

import android.view.View
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alokomkar.btc.base.BaseViewModel
import com.google.android.material.snackbar.Snackbar

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.changeVisibility(isVisible : Boolean ) {
    if( isVisible ) show() else hide()
}

fun View.showSnackBar( message : String, retry : Int?, retryListener : (() -> Unit)? = null) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).apply {
        retry?.also { setAction(this.context.getString(retry)) {
            retryListener?.invoke()
        } }
    }.show()
}

fun <L : LiveData<T>, T : Any> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))
