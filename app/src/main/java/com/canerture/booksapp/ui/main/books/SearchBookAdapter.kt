package com.canerture.booksapp.ui.main.books

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.canerture.booksapp.R
import com.canerture.booksapp.databinding.BookSearchViewBinding

class SearchBookAdapter : RecyclerView.Adapter<SearchBookAdapter.BookSearchViewDesign>() {

    var searchText: (String?) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookSearchViewDesign {
        val layoutInflater = LayoutInflater.from(parent.context)
        val bookSearchViewBinding = BookSearchViewBinding.inflate(layoutInflater, parent, false)
        return BookSearchViewDesign(bookSearchViewBinding)
    }

    override fun onBindViewHolder(holder: BookSearchViewDesign, position: Int) {
        holder.bind()
    }

    inner class BookSearchViewDesign(private var bookSearchViewBinding: BookSearchViewBinding) :
        RecyclerView.ViewHolder(bookSearchViewBinding.root) {

        fun bind() {

            bookSearchViewBinding.searchView.setOnQueryTextListener(object :
                SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    searchText(query)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText.isNullOrEmpty()) {
                        searchText(newText)
                    }
                    return false
                }
            })
        }

    }

    override fun getItemCount(): Int = 1

}