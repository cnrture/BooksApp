package com.canerture.booksapp.ui.main.books

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.canerture.booksapp.databinding.BooksListBinding
import com.canerture.booksapp.data.model.BookModel
import com.canerture.booksapp.data.model.BooksBasketRoomModel
import kotlin.collections.ArrayList

class BooksListAdapter : RecyclerView.Adapter<BooksListAdapter.BooksListDesign>(), Filterable {

    private val booksList = ArrayList<BookModel>()
    var booksFilterList = ArrayList<BookModel>()
    var onAddBasketClick: (BooksBasketRoomModel) -> Unit = {}

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

        fun bind(bookModelFilterList: ArrayList<BookModel>) {

            booksListBinding.booksRecycler.apply {
                adapter = BooksItemAdapter()
                    .also {
                        it.updateList(bookModelFilterList)
                        it.onAddBasketClick = onAddBasketClick
                    }
            }

        }

    }

    override fun getItemCount(): Int = 1

    fun updateList(list: List<BookModel>) {
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
                    val resultList = ArrayList<BookModel>()
                    for (row in booksList) {
                        row.book_name?.let { bookName ->
                            row.book_author?.let { bookAuthor ->
                                row.book_publisher?.let { bookPublisher ->
                                    if (bookName.lowercase().contains(searchText) ||
                                        bookAuthor.lowercase().contains(searchText) ||
                                        bookPublisher.lowercase().contains(searchText)
                                    ) {
                                        resultList.add(row)
                                    }
                                }
                            }
                        }
                    }
                    resultList
                }

                val filterResults = FilterResults()
                filterResults.values = booksFilterList

                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                booksFilterList = results?.values as ArrayList<BookModel>
                notifyDataSetChanged()
            }

        }
    }
}