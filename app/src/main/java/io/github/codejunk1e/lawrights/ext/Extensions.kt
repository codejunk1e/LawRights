package io.github.codejunk1e.lawrights.ext

import android.util.DisplayMetrics
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun Int.dpToPx(displayMetrics: DisplayMetrics): Int = (this * displayMetrics.density).toInt()

fun View.show() { this.visibility = View.VISIBLE }
fun View.hide() { this.visibility = View.GONE }

fun <T, K, R> LiveData<T>.combineWith(
    liveData: LiveData<K>,
    block: (T?, K?) -> R
): LiveData<R> {
    val result = MediatorLiveData<R>()
    result.addSource(this) {
        result.value = block(this.value, liveData.value)
    }
    result.addSource(liveData) {
        result.value = block(this.value, liveData.value)
    }
    return result
}