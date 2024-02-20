package com.example.learnandroid.learnviewpager

import android.view.View
import androidx.viewpager.widget.ViewPager

class ZoomOutPageTransformer : ViewPager.PageTransformer {
    companion object {
        const val MIN_SCALE = 0.8f
        const val MIN_ALPHA = 0.6f
    }

    override fun transformPage(page: View, position: Float) {
        var position = position
        if (position < -1) {
            page.apply {
                alpha = MIN_ALPHA
                scaleX = MIN_SCALE
                scaleY = MIN_SCALE
            }
        } else if (position <= 1) {
            if (position < 0) {
                if (position < -0.2f) {
                    position = -0.2f
                }
                page.apply {
                    alpha = 1f + position * 2
                    scaleY = 1f + position
                    scaleY = 1f + position
                }
            } else {
                if (position > 0.2) {
                    position = 0.2f
                }
                page.apply {
                    alpha = 1f - position * 2
                    scaleY = 1f - position
                    scaleX = 1f - position
                }
            }
        } else {
            page.apply {
                alpha = MIN_ALPHA
                scaleX = MIN_SCALE
                scaleY = MIN_SCALE
            }
        }
    }
}