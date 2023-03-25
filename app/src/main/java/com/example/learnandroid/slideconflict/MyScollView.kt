package com.example.learnandroid.slideconflict

import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ScrollView

// 父亲view
// 内部拦截
class MyScollView @JvmOverloads constructor(
    context: android.content.Context,
    attrs: android.util.AttributeSet? = null,
    defStyleAttr: Int = 0
): ScrollView(
    context,
    attrs,
    defStyleAttr
) {

    companion object {
        const val TAG = "MyScollView"
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
        return super.dispatchTouchEvent(ev)
    }

    override fun onNestedPreScroll(target: View?, dx: Int, dy: Int, consumed: IntArray?) {
        super.onNestedPreScroll(target, dx, dy, consumed)
        Log.d(TAG, "onNestedPreScroll: $dx, $dy, $consumed")
    }
}