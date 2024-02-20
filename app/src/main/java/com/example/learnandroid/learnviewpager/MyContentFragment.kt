package com.example.learnandroid.learnviewpager

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.learnandroid.R
import com.example.learnandroid.databinding.FragmentMyContentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MyContentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyContentFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentMyContentBinding? = null

    private var tabIndex: Int = 0
    var con: CountDownTimer? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            tabIndex = it.getInt(INTENT_INT_INDEX)
        }
        Log.d(TAG, "$tabIndex fragment onCreate: ")
        lifecycleScope.launch {
            Log.d(TAG, "start ${Thread.currentThread()}")
            withContext(Dispatchers.Main) {
                Log.d(TAG, "ing ${Thread.currentThread()}")
            }
            Log.d(TAG, "end ${Thread.currentThread()}")
        }

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "$tabIndex fragment onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "$tabIndex fragment onPause: ")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "$tabIndex fragment onDetach: ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        con?.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        Log.i(
            TAG,
            "$tabIndex fragment setUserVisibleHint: $isVisibleToUser"
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMyContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun getData() {
        con = object : CountDownTimer(1000, 100) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                handler.sendEmptyMessage(0)
            }
        }.apply {
            start()
        }
    }

    private val handler: Handler = object : Handler() {
        @SuppressLint("HandlerLeak")
        override fun handleMessage(msg: Message) {
            binding.ivContent.setVisibility(View.GONE)
            val id: Int = when (tabIndex) {
                1 -> R.drawable.a
                2 -> R.drawable.b
                3 -> R.drawable.c
                4 -> R.drawable.d
                else -> R.drawable.a
            }

            // 这是我注释掉的，备用的代码而已哦，同学们
            /*imageView.setImageResource(id);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setVisibility(View.VISIBLE);
            Log.d(TAG, tabIndex +" handleMessage: " );
            //模拟耗时工作
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "$tabIndex fragment onAttach: ")
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MyContentFragment.
         */
        const val TAG = "MyContentFragment"
        const val INTENT_INT_INDEX = "index"

        @JvmStatic
        fun newInstance(tabIndex: Int) =
            MyContentFragment().apply {
                arguments = Bundle().apply {
                    putInt(INTENT_INT_INDEX, tabIndex)
                }
            }
    }
}