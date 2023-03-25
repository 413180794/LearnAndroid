package com.example.learnandroid.slideconflict

import android.util.Log
import android.view.MotionEvent

class MyListView @JvmOverloads constructor(
context: android.content.Context,
    attrs: android.util.AttributeSet? = null,
    defStyleAttr: Int = 0
): android.widget.ListView(
    context,
    attrs,
    defStyleAttr
) {
    companion object {
        const val TAG = "MyListView"
    }
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        // 子view控制父亲是否拦截事件，所以这里除了down事件，都认为拦截掉。
        // 这样，如果孩子不处理，move事件是不可能传递到孩子
        val intercept = super.onInterceptTouchEvent(ev)

        Log.d(TAG, "onInterceptTouchEvent: ${ev?.action} $intercept")
        return intercept
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.d(TAG, "dispatchTouchEvent: ${ev?.action}")
        when (ev?.action) {
            MotionEvent.ACTION_MOVE -> {
                parent.requestDisallowInterceptTouchEvent(true)
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}