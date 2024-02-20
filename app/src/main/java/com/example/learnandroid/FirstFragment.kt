package com.example.learnandroid

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.learnandroid.databinding.FragmentFirstBinding
import com.example.learnandroid.learnviewpager.MyContentFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenResumed {
            Log.d(MyContentFragment.TAG, "start ${Thread.currentThread()}")
            withContext(Dispatchers.Main) {
                Log.d(MyContentFragment.TAG, "ing ${Thread.currentThread()}")
            }
            Log.d(MyContentFragment.TAG, "end ${Thread.currentThread()}")
        }
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.slideConfict.startAnimation(AlphaAnimation(0f,1f).apply {
            duration = 20 * 1000
        })
        binding.slideConfict
        binding.slideConfict.setOnClickListener{
            findNavController().navigate(R.id.action_FirstFragment_to_slideFragment)
        }
        binding.learnViewPager.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_viewPagerFragment)
        }
        binding.learnRecyclerView.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_RecyclerViewFragment)
        }
        binding.learnDrawableView.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_LearnDrawableFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}