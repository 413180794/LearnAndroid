package com.example.learnandroid.learnrecyclerview.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class C2PaddingDecoration(val padding: Int, val needTopPadding: Boolean): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val halfPadding = padding / 2f
        super.getItemOffsets(outRect, view, parent, state)
        if (needTopPadding){
            val pos = parent.getChildAdapterPosition(view)
            if (pos == 0 || pos == 1){
                outRect.set(Rect(padding, padding * 2, padding, padding * 2))
                return
            }
        }
        outRect.set(Rect(padding, 0, padding, padding * 2))
    }
}