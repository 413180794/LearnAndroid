package com.example.learnandroid.slideconflict

import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout
import android.widget.ScrollView

// 父亲view
// 内部拦截
class MyScollViewLinearLayout @JvmOverloads constructor(
    context: android.content.Context,
    attrs: android.util.AttributeSet? = null,
    defStyleAttr: Int = 0
): LinearLayout(
    context,
    attrs,
    defStyleAttr
) {

    companion object {
        const val TAG = "MyScollViewLinearLayout"
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        // 子view控制父亲是否拦截事件，所以这里除了down事件，都认为拦截掉。
        // 这样，如果孩子不处理，move事件是不可能传递到孩子的
        Log.d(TAG, "onInterceptTouchEvent: ${ev?.action}")
        return when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                super.onInterceptTouchEvent(ev)
            }
            else -> {
                super.onInterceptTouchEvent(ev)
            }
        }
    }

}