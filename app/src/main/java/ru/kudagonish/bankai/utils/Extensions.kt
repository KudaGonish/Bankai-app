package ru.kudagonish.bankai.utils

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView

fun TextView.setViewPadding(
    left: Float? = null, top: Float? = null,
    right: Float? = null, bottom: Float? = null,
    horizontal: Float? = null, vertical: Float? = null,
    all: Float? = null
) {
    if (listOfNotNull(left, top, right, bottom, horizontal, vertical, all).any { it < 0f }) return
    all?.let { setPadding(it.toInt(), it.toInt(), it.toInt(), it.toInt()) }
    horizontal?.let { setPadding(paddingLeft, it.toInt(), paddingRight, it.toInt()) }
    vertical?.let { setPadding(it.toInt(), paddingTop, it.toInt(), paddingBottom) }
    setPadding(
        left?.toInt() ?: paddingLeft,
        top?.toInt() ?: paddingTop,
        right?.toInt() ?: paddingRight,
        bottom?.toInt() ?: paddingBottom
    )
}

var View.layoutGravity
    get() = (layoutParams as FrameLayout.LayoutParams).gravity
    set(value) {
        layoutParams = FrameLayout.LayoutParams(
            layoutParams.width,
            layoutParams.height,
            value
        )
    }

fun View.setMargin(leftMargin: Int? = null, topMargin: Int? = null,
                   rightMargin: Int? = null, bottomMargin: Int? = null) {
    val params = layoutParams as ViewGroup.MarginLayoutParams
    params.setMargins(
        leftMargin ?: params.leftMargin,
        topMargin ?: params.topMargin,
        rightMargin ?: params.rightMargin,
        bottomMargin ?: params.bottomMargin)
    layoutParams = params
}
