package com.levine.gourmetrecipes.extension

import android.os.SystemClock
import android.view.View
import android.view.View.*

var View.visible
    get() = visibility == VISIBLE
    set(value) {
        visibility = if (value) VISIBLE else GONE
    }

/**
 * 显示View
 */
fun View.show() {
    visibility = VISIBLE
}
/**
 * 隐藏View
 * @param gone 是否是gone
 */
fun View.hide(gone: Boolean = true) {
    visibility = if (gone) GONE else INVISIBLE
}

/**
 * View的防抖点击
 */
fun View.setOnDebouncedClickListener(action: () -> Unit) {
    val actionDebouncer = ActionDebouncer(action)
    setOnClickListener {
        actionDebouncer.notifyAction()
    }
}

fun View.removeDebouncedClickListener() {
    setOnClickListener(null)
    isClickable = false
}

private class ActionDebouncer(val action: () -> Unit) {
    companion object {
        const val DEBOUNCE_INTERVAL_MILLISECONDS = 600L
    }

    private var lastActionTime = 0L

    fun notifyAction() {
        val now = SystemClock.elapsedRealtime()
        val msPassed = now - lastActionTime
        val actionAllow = msPassed > DEBOUNCE_INTERVAL_MILLISECONDS
        lastActionTime = now

        if (actionAllow) {
            action.invoke()
        }
    }
}
