package io.github.codejunk1e.lawrights.ext

import android.util.DisplayMetrics
import android.view.View

fun Int.dpToPx(displayMetrics: DisplayMetrics): Int = (this * displayMetrics.density).toInt()

fun View.show() { this.visibility = View.VISIBLE }
fun View.hide() { this.visibility = View.GONE }