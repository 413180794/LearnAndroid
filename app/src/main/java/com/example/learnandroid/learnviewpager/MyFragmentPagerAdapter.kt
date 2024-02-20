package com.example.learnandroid.learnviewpager

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyFragmentPagerAdapter(fm: FragmentManager, private val flist: MutableList<Fragment>) :
    FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return flist.size
    }

    override fun getItem(position: Int): Fragment {
        return flist[position]
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }
}