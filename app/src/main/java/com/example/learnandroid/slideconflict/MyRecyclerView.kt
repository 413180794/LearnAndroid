package com.example.learnandroid.slideconflict

import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.recyclerview.widget.RecyclerView
import com.example.learnandroid.R


// 子view
class MyRecyclerView @JvmOverloads constructor(
    context: android.content.Context,
    attrs: android.util.AttributeSet? = null,
    defStyleAttr: Int = 0
): RecyclerView(context, attrs, defStyleAttr) {

    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
        Log.d("MyRecyclerView", "onInterceptTouchEvent: ${e?.action}")
        return super.onInterceptTouchEvent(e)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.d("MyRecyclerView", "dispatchTouchEvent: ${ev?.action}")
        return super.dispatchTouchEvent(ev)
    }



}