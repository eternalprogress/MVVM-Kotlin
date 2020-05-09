package com.joker.mvvm.kotlin.basemvvm.ext

/**
 * @Author joker
 * @Date 2020/5/9-22:37
 */
fun android.view.View.onClick(l: (v: android.view.View?) -> Unit) {
    setOnClickListener(l)
}

fun android.view.View.onLongClick(l: (v: android.view.View?) -> Boolean) {
    setOnLongClickListener(l)
}

fun android.view.View.onSystemUiVisibilityChange(l: (visibility: Int) -> Unit) {
    setOnSystemUiVisibilityChangeListener(l)
}

fun android.view.View.onTouch(l: (v: android.view.View, event: android.view.MotionEvent) -> Boolean) {
    setOnTouchListener(l)
}

fun android.view.View.onCreateContextMenu(l: (menu: android.view.ContextMenu?, v: android.view.View?, menuInfo: android.view.ContextMenu.ContextMenuInfo?) -> Unit) {
    setOnCreateContextMenuListener(l)
}

fun android.view.View.onDrag(l: (v: android.view.View, event: android.view.DragEvent) -> Boolean) {
    setOnDragListener(l)
}

fun android.view.View.onFocusChange(l: (v: android.view.View, hasFocus: Boolean) -> Unit) {
    setOnFocusChangeListener(l)
}