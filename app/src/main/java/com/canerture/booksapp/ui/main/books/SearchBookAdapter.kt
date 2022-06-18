package com.canerture.booksapp.ui.main.books

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.canerture.booksapp.databinding.BookSearchViewBinding

class SearchBookAdapter : RecyclerView.Adapter<SearchBookAdapter.BookSearchViewDesign>() {

    var searchText: (String?) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookSearchViewDesign {
        val bookSearchViewBinding =
            BookSearchViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookSearchViewDesign(bookSearchViewBinding)
    }

    override fun onBindViewHolder(holder: BookSearchViewDesign, position: Int) {
        holder.bind()
    }

    inner class BookSearchViewDesign(private var bookSearchViewBinding: BookSearchViewBinding) :
        RecyclerView.ViewHolder(bookSearchViewBinding.root) {

        fun bind() {


        }
    }

    override fun getItemCount(): Int = 1
}