package com.example.learnandroid.learnviewpager

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.learnandroid.R

class MyPagerAdapter(private val items: MutableList<Int>) : PagerAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    override fun getPageWidth(position: Int): Float {

        return super.getPageWidth(position)
    }

    override fun getItemPosition(`object`: Any): Int {
        items.forEachIndexed { index, view ->
            if (view == (`object` as? View)?.tag) {
                return index
            }
        }
        return POSITION_NONE
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        val view = views[position]
        val colors = mutableListOf<Int>().apply {
            add(Color.RED)
            add(Color.GREEN)
            add(Color.RED)
            add(Color.WHITE)
            add(Color.GREEN)
        }
       val view = LayoutInflater.from(container.context).inflate(R.layout.view_pager_content, null)
        view.tag = items[position]
        view.setBackgroundColor(colors[position])
        view.findViewById<TextView>(R.id.vc_text).text = "" + position
        container.addView(view)
        return view
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as? View)
    }

}