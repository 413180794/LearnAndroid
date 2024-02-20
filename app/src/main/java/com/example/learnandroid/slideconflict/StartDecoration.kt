package com.example.learnandroid.slideconflict

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StartDecoration(context: Context) : RecyclerView.ItemDecoration() {
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        (parent.adapter as? BookAdapter)?.let {
            val childCount = parent.childCount
            val left = parent.paddingLeft
            val right = parent.width - parent.paddingRight
            for (i in 0 until childCount) {
                val view = parent.getChildAt(i);
                val position = parent.getChildAdapterPosition(view);
                val isGroupHeader = it.isGroupHeader(position)
                if (isGroupHeader) {
                    c.drawRect(
                        left.toFloat(), view.top - groupHeaderHeight, right.toFloat(),
                        view.top.toFloat(), Paint().apply {
                            color = Color.RED
                        })
                    val authorName = it.getGroupName(position)
                    val textPaint = Paint().apply {
                        color = Color.WHITE
                    }
                    val textRect = Rect()
                    textPaint.getTextBounds(authorName, 0, authorName.length, textRect)
                    c.drawText(
                        authorName,
                        (left + 20).toInt().toFloat(),
                        view.top.toInt() - (groupHeaderHeight / 2 + textRect.height() / 2).toFloat(),
                        Paint().apply {
                            color = Color.WHITE
                            textSize = dp2px(parent.context, 25)
                        })
                } else {
                    c.drawRect(
                        left.toFloat(), (view.top - 4).toFloat(), right.toFloat(),
                        view.top.toFloat(), Paint().apply {
                            color = Color.WHITE
                        })
                }
            }
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        (parent.adapter as? BookAdapter)?.let {
            (parent.layoutManager as? LinearLayoutManager)?.let { lm ->
                val position = lm.findFirstVisibleItemPosition()
                val holder =
                    parent.findViewHolderForAdapterPosition(position)

                val itemView = holder?.itemView
                val left = parent.paddingLeft
                val right = parent.width - parent.paddingRight
                val top = parent.paddingTop
                val isGroupHeader = it.isGroupHeader(position+1)
                if (isGroupHeader) {
                    val bottom = groupHeaderHeight.toInt().coerceAtMost(itemView?.bottom ?: 0)
                    c.drawRect(left.toFloat(), (bottom - groupHeaderHeight).toFloat(), right.toFloat(),
                        bottom.toFloat(),Paint().apply {
                        color = Color.RED
                    })
                    val textPaint = Paint().apply {
                        color = Color.WHITE
                    }

                    val authorName = it.getGroupName(position)
                    val textRect = Rect()
                    textPaint.getTextBounds(authorName, 0, authorName.length, textRect)
                    c.drawText(
                        authorName,
                        (left + 20).toInt().toFloat(),
                        (top.toInt() + bottom - groupHeaderHeight / 2 + textRect.height() / 2).toFloat(),
                        Paint().apply {
                            color = Color.WHITE
                            textSize = dp2px(parent.context, 25)
                        })
                } else {
                    c.drawRect(left.toFloat(), top.toFloat(),
                        right.toFloat(), top + groupHeaderHeight, Paint().apply {
                            color = Color.RED
                        })
                    val authorName = it.getGroupName(position)
                    val textPaint = Paint().apply {
                        color = Color.WHITE
                    }
                    val textRect = Rect()
                    textPaint.getTextBounds(authorName, 0, authorName.length, textRect)
                    c.drawText(
                        authorName,
                        (left + 20).toInt().toFloat(),
                        top.toInt() + (groupHeaderHeight / 2 + textRect.height() / 2).toFloat(),
                        Paint().apply {
                            color = Color.WHITE
                            textSize = dp2px(parent.context, 25)
                        })
                }
            }
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        // how 怎么判断是头部

        (parent.adapter as? BookAdapter)?.let {
            val position = parent.getChildAdapterPosition(view)
            val isGroupHeader = it.isGroupHeader(position)
            if (isGroupHeader) {
                outRect.set(0, groupHeaderHeight.toInt(), 0, 0)
            } else {
                outRect.set(0, 4, 0, 0)
            }

        }
    }

    private val groupHeaderHeight = dp2px(context, 100)
    private fun dp2px(context: Context, dpValue: Int): Float {
        val scale = context.resources.displayMetrics.density
        return dpValue * scale * 0.5f
    }
}