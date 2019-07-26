package com.alokomkar.btc.extension

import android.view.View

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.changeVisibility(isVisible : Boolean ) {
    if( isVisible ) show() else hide()
}