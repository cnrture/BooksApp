package com.canerture.booksapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.canerture.booksapp.databinding.BooksListBinding
import com.canerture.booksapp.model.Books
import kotlin.collections.ArrayList

class BooksListAdapter : RecyclerView.Adapter<BooksListAdapter.BooksListDesign>(), Filterable {

    private val booksList = ArrayList<Books>()
    var booksFilterList = ArrayList<Books>()

    init {
        booksFilterList = booksList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksListDesign {
        val layoutInflater = LayoutInflater.from(parent.context)
        val bookListBinding = BooksListBinding.inflate(layoutInflater, parent, false)
        return BooksListDesign(bookListBinding)
    }

    override fun onBindViewHolder(holder: BooksListDesign, position: Int) {
        holder.bind(booksFilterList)
    }

    inner class BooksListDesign(private var booksListBinding: BooksListBinding) :
        RecyclerView.ViewHolder(booksListBinding.root) {

        fun bind(booksFilterList: ArrayList<Books>) {

            booksListBinding.booksRecycler.apply {
                adapter = BooksItemAdapter()
                    .also {
                        it.updateList(booksFilterList)
                    }
            }

        }

    }

    override fun getItemCount(): Int = 1

    fun updateList(list: List<Books>) {
        booksList.clear()
        booksList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {

        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {

                val searchText = constraint.toString().lowercase()
                booksFilterList = if (searchText.isEmpty()) {
                    booksList
                } else {
                    val resultList = ArrayList<Books>()
                    for (row in booksList) {
                        if (row.book_name.lowercase().contains(searchText) ||
                            row.book_author.lowercase().contains(searchText) ||
                            row.book_publisher.lowercase().contains(searchText)
                        ) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }

                val filterResults = FilterResults()
                filterResults.values = booksFilterList

                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                booksFilterList = results?.values as ArrayList<Books>
                notifyDataSetChanged()
            }

        }
    }
}