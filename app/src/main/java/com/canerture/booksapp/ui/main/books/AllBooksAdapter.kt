package com.canerture.booksapp.ui.main.books

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.canerture.booksapp.data.model.Book
import com.canerture.booksapp.databinding.BookItemBinding
import com.squareup.picasso.Picasso

class AllBooksAdapter : RecyclerView.Adapter<AllBooksAdapter.BookItemDesign>(), Filterable {

    private val booksList = ArrayList<Book>()
    var booksFilterList = ArrayList<Book>()

    var onAddBasketClick: (Book) -> Unit = {}
    var onBookClick: (Book) -> Unit = {}

    init {
        booksFilterList = booksList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemDesign {
        val binding = BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookItemDesign(binding)
    }

    override fun onBindViewHolder(holder: BookItemDesign, position: Int) =
        holder.bind(booksFilterList[position])

    inner class BookItemDesign(private var binding: BookItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {

            with(binding) {

                bookModel = book

                book.bookImageUrl?.let {
                    Picasso.get().load(it).into(bookImageView)
                }

                bookImageView.setOnClickListener {
                    onBookClick(book)
                }

                addBasketImage.setOnClickListener {
                    onAddBasketClick(book)
                }
            }
        }
    }

    override fun getItemCount(): Int = booksFilterList.size

    fun updateList(list: List<Book>) {
        booksList.clear()
        booksList.addAll(list)
        notifyItemRangeInserted(0, list.size)
    }

    override fun getFilter(): Filter {

        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {

                val searchText = constraint.toString().lowercase()
                booksFilterList = if (searchText.isEmpty()) {
                    booksList
                } else {
                    val resultList = ArrayList<Book>()
                    for (row in booksList) {
                        row.bookName?.let { bookName ->
                            row.bookAuthor?.let { bookAuthor ->
                                row.bookPublisher?.let { bookPublisher ->
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
                booksFilterList = results?.values as ArrayList<Book>
                notifyItemRangeChanged(0, booksFilterList.size)
            }
        }
    }
}