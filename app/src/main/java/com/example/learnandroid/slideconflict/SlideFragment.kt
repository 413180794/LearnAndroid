package com.example.learnandroid.slideconflict

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SimpleAdapter
import androidx.navigation.fragment.findNavController
import com.example.learnandroid.databinding.FragmentSecondBinding
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

        _binding = FragmentSlideBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()
    }

    private fun setView(){
        setHeadTextView()
        setFooterTextView()
        setRecyclerView()

    }
    private fun setHeadTextView(){
        binding.textViewHead.text = "我被心上人灌下晕药，送到了新科状元的床上。\n" +
                "\n" +
                "一年后的雨水，我被人毒死，扔进枯井之中。\n" +
                "\n" +
                "死前，我竟然听到了撕心裂肺的哭声。\n" +
                "\n" +
                "1.\n" +
                "\n"

    }
    private fun setFooterTextView(){
        binding.textViewFoot.text = "  \"我再醒来的这日，是雨水。\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"手中算盘扣得愈发急促，我知道，再过一刻钟，遭到山贼掳劫的顾璋就会奄奄一息地晕倒在客栈后院。\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"而我赶在下雨前去收晒在后院的册子，接着发现半身血的顾璋昏倒在门槛前。\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"“我本万念俱灰，却得见姑娘素衣清透，擎伞而来，那一刻的摄魂夺魄至今未敢忘。”\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"等顾璋伤好后，他会一身月白长衫立在小院杏花下，在春日微风中，与我认认真真一揖，认认真真倾诉思慕之意。\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"杏花簌簌，不及他弯眉。\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"可我已不再会措手不及，脸颊通红了。\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"“小二！你去把后院的门关严实了，免得什么阿猫阿狗都能爬进来，顺道把晒着的册子都收了。”\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"“得嘞。”\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"这一世，没有必要再相遇了。\\n\""
    }
    private fun setRecyclerView(){
        val bookList = ArrayList<Book>()
        for (i in 0..50){
            val book = Book("Book $i", "Author $i", i.toDouble())
            bookList.add(book)
        }
        val bookAdapter = BookAdapter(bookList.toTypedArray())
        binding.recyclerView.apply {
            adapter = ArrayAdapter (requireContext(), android.R.layout.simple_list_item_1, bookList)

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}