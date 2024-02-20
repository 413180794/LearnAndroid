package com.example.learnandroid.learnviewpager

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.learnandroid.R
import com.example.learnandroid.databinding.FragmentViewPagerBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ViewPagerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewPagerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentViewPagerBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val onNavigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(@NonNull item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.fragment_1 -> {
                        binding.viewPager.setCurrentItem(0, true)
                        return true
                    }

                    R.id.fragment_2 -> {
                        binding.viewPager.setCurrentItem(1, true)
                        return true
                    }

                    R.id.fragment_3 -> {
                        binding.viewPager.setCurrentItem(2, true)
                        return true
                    }

                    R.id.fragment_4 -> {
                        binding.viewPager.setCurrentItem(3, true)
                        return true
                    }

                    R.id.fragment_5 -> {
                        binding.viewPager.setCurrentItem(4, true)
                        return true
                    }
                }
                return false
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(
            onNavigationItemSelectedListener
        )


        val views = mutableListOf<Int>().apply {
            add(0)

            add(1)

            add(2)
            add(3)
            add(0)

        }
        val adapter = MyPagerAdapter(views)
//        val adapter = fragmentManager?.let { MyPagerAdapter(it, getShowData()) }
        binding.viewPager.adapter = adapter

//        binding.viewPager.setPageTransformer(true, ZoomOutPageTransformer())
        binding.viewPager.pageMargin = 0
        binding.viewPager.addOnPageChangeListener(viewpagerChangeListener)
        binding.dataChanged.setOnClickListener {
            binding.viewPager?.adapter?.notifyDataSetChanged()
        }
    }

    private val viewpagerChangeListener: ViewPager.OnPageChangeListener =
        object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(i: Int, v: Float, i1: Int) {}
            override fun onPageSelected(i: Int) {
                Log.d("zhang-debug", "onpage $i")
                var itemId = R.id.fragment_1
                when (i) {
                    0 -> itemId = R.id.fragment_1
                    1 -> itemId = R.id.fragment_2
                    2 -> itemId = R.id.fragment_3
                    3 -> itemId = R.id.fragment_4
                    4 -> {
                        itemId = R.id.fragment_5
                    }

                    5 -> {
                        itemId = R.id.fragment_5
                    }
                }
                binding.bottomNavigationView.selectedItemId = itemId
            }

            override fun onPageScrollStateChanged(i: Int) {}
        }

    private fun getShowData(): MutableList<Fragment> {
        val fragmentList: MutableList<Fragment> = ArrayList()
        fragmentList.add(MyContentFragment.newInstance(1))
        fragmentList.add(MyContentFragment.newInstance(2))
        fragmentList.add(MyContentFragment.newInstance(3))
        fragmentList.add(MyContentFragment.newInstance(4))
        fragmentList.add(MyContentFragment.newInstance(5))
        fragmentList.add(MyContentFragment.newInstance(6))
        return fragmentList
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ViewPagerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ViewPagerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}