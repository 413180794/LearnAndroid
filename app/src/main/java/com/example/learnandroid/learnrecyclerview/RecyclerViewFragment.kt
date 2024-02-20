package com.example.learnandroid.learnrecyclerview

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.learnandroid.R
import com.example.learnandroid.databinding.FragmentRecyclerViewBinding
import com.example.learnandroid.learnrecyclerview.adapter.C2PaddingDecoration
import com.example.learnandroid.learnrecyclerview.adapter.FadeInAnimator
import com.example.learnandroid.learnrecyclerview.adapter.UniversalAdapter
import com.example.learnandroid.learnrecyclerview.adapter.ViewHolder
import kotlin.properties.Delegates

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

fun dip2Px(context: Context?, dipValue: Float): Float {
    if (context != null) {
        val scale = context.resources.displayMetrics.density
        return dipValue * scale + 0.5f
    }
    return 0f
}


/**
 * A simple [Fragment] subclass.
 * Use the [RecyclerViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecyclerViewFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var rv: RecyclerView? = null
    private var adapter: UniversalAdapter<SlideCardBean>? = null
    private var mDatas: List<SlideCardBean>? = null

    private var _binding: FragmentRecyclerViewBinding? = null
    private var name: String by Delegates.observable("初始值") { prop, old, new ->
        {
            println("$old $new $prop")
        }
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv = binding.rv
        rv!!.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val decoration = C2PaddingDecoration(dip2Px(context, 2f).toInt(), false)
        rv!!.addItemDecoration(decoration)
        rv?.setPadding(dip2Px(context, 2f).toInt(), 0, dip2Px(context, 2f).toInt(), 0)
        rv!!.itemAnimator = FadeInAnimator()
        mDatas = SlideCardBean.initDatas()
        adapter =
            object :
                UniversalAdapter<SlideCardBean>(this.context, mDatas, R.layout.item_swipe_card) {
                override fun convert(viewHolder: ViewHolder, slideCardBean: SlideCardBean) {
                    viewHolder.setText(R.id.tvName, slideCardBean.name)
                    viewHolder.setText(
                        R.id.tvPrecent,
                        "" + slideCardBean.postition + "/" + mDatas.size
                    )
                    viewHolder?.setOnClickListener(R.id.button) {
                        val x = viewHolder.getView<FrameLayout>(R.id.father)

                        Log.d("zhang-debug",
                            (x.layoutParams as StaggeredGridLayoutManager.LayoutParams).spanIndex.toString()
                        )
                        val currentItemSpanIndex =  (x.layoutParams as StaggeredGridLayoutManager.LayoutParams).spanIndex
                        // 接下来看，下一个或者下下一个 holder 的 spanindex
                        val insertPosition = viewHolder.adapterPosition + 2
                        mDatas.add(
                            SlideCardBean(insertPosition,
                                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595434963213&di=5d07d9de35f42c16238c3076119a6e98&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fmobile%2F2018-12-13%2F5c120783eba2b.jpg",
                                "美女$120"
                            ))
                        this.notifyItemRangeInserted(insertPosition, 1)
                    }
                    Glide.with(this@RecyclerViewFragment)
                        .load(slideCardBean.url)
                        .into(viewHolder.getView(R.id.iv) as ImageView)
                }
            }

        rv!!.adapter = adapter
        // 初始化数据
        // 初始化数据
        CardConfig.initConfig(this.context)

        val slideCallback = SlideCallback(rv, adapter, mDatas)
        val itemTouchHelper = ItemTouchHelper(slideCallback)
//        itemTouchHelper.attachToRecyclerView(rv)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RecyclerViewFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecyclerViewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}