package com.kuit.ourmenu.utils.dragndrop

import android.util.Log
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.zIndex

fun <T> MutableList<T>.move(from: Int, to: Int) {
    if (from == to) return
    if (to > this.size - 1) return
    Log.d("DragAndDrop", "Moving item from $from to $to , ${this.size}")
    val element = this.removeAt(from)
    try {
        this.add(to, element)
    } catch (e: IndexOutOfBoundsException) {
        this.add(element)
    }
}

fun Modifier.dragModifier(index: Int, dragAndDropListState: DragAndDropListState) = composed {
    val isDragging = index == dragAndDropListState.currentIndexOfDraggedItem
    val offsetOrNull = dragAndDropListState.elementDisplacement.takeIf { isDragging }

    this
        .zIndex(if (isDragging) 1f else 0f)
        .graphicsLayer {
            translationY = offsetOrNull ?: 0f
            scaleX = if (isDragging) 1.03f else 1f
            scaleY = if (isDragging) 1.03f else 1f
        }
}