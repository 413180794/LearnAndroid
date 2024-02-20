package com.example.learnandroid.slideconflict


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learnandroid.databinding.BookItemBinding


class BookAdapter(private val dataSet: Array<Book>) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    class BookViewHolder(bookItemBinding: BookItemBinding) :
        RecyclerView.ViewHolder(bookItemBinding.root) {
        val bookName: TextView = bookItemBinding.name
        val bookAuthor: TextView = bookItemBinding.author
        val bookPrice: TextView = bookItemBinding.price
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    fun isGroupHeader(position: Int): Boolean {
        if (position == 0) {
            return true
        } else {
            val groupName = getGroupName(position)
            val preGroupName = getGroupName(position - 1)
            if (groupName != preGroupName) {
                return true
            }
            return false
        }
    }

    fun getGroupName(position: Int): String {
        return dataSet[position].author
    }
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = dataSet[position]
        holder.bookName.text = book.name
        holder.bookAuthor.text = book.author
        holder.bookPrice.text = book.price.toString()
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}