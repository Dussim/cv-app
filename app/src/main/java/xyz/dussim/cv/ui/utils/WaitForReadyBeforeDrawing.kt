package xyz.dussim.cv.ui.utils

import android.app.Activity
import android.view.View
import android.view.ViewTreeObserver

class WaitForReadyBeforeDrawing(
    private val parent: View,
    private val isReady: () -> Boolean
) : ViewTreeObserver.OnPreDrawListener {

    init {
        parent.viewTreeObserver.addOnPreDrawListener(this)
    }

    override fun onPreDraw(): Boolean {
        return if (isReady()) {
            parent.viewTreeObserver.removeOnPreDrawListener(this)
            true
        } else {
            false
        }
    }
}

fun Activity.waitBeforeDrawing(until: () -> Boolean) {
    WaitForReadyBeforeDrawing(
        parent = findViewById(android.R.id.content),
        isReady = until
    )
}
