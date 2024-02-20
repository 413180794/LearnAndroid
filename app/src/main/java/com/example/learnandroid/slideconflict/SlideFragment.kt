package com.example.learnandroid.slideconflict

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.learnandroid.databinding.FragmentSlideBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SlideFragment : Fragment() {

    private var _binding: FragmentSlideBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        HelloWorld().he()
        _binding = FragmentSlideBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()
    }

    private fun setView() {
        setRecyclerView()

    }

    private fun setRecyclerView() {
        val bookList = ArrayList<Book>()
        for (i in 0..25) {
            val book = Book("Book $i", "Author:zhangfan", i.toDouble())
            bookList.add(book)
        }
        for (i in 0..25) {
            val book = Book("Book $i", "Author:lisi", i.toDouble())
            bookList.add(book)
        }
        val bookAdapter = BookAdapter(bookList.toTypedArray())
        binding.recyclerView.apply {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            adapter = bookAdapter
            addItemDecoration(StartDecoration(context))
            PagerSnapHelper().attachToRecyclerView(this)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}