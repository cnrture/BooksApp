package com.canerture.booksapp.ui.main.books

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.canerture.booksapp.data.model.BookModel
import com.canerture.booksapp.data.model.BooksBasketRoomModel
import com.canerture.booksapp.databinding.BookItemBinding
import com.squareup.picasso.Picasso

class AllBooksAdapter : RecyclerView.Adapter<AllBooksAdapter.BookItemDesign>(), Filterable {

    private val booksList = ArrayList<BookModel>()
    var booksFilterList = ArrayList<BookModel>()
    var onAddBasketClick: (BooksBasketRoomModel) -> Unit = {}

    init {
        booksFilterList = booksList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemDesign {
        val bookItemBinding =
            BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookItemDesign(bookItemBinding)
    }

    override fun onBindViewHolder(holder: BookItemDesign, position: Int) {
        holder.bind(booksFilterList[position])
    }

    inner class BookItemDesign(private var bookItemBinding: BookItemBinding) :
        RecyclerView.ViewHolder(bookItemBinding.root) {

        fun bind(book: BookModel) {

            bookItemBinding.apply {

                bookModel = book

                book.book_image_url?.let {
                    Picasso.get().load(it).into(bookImageView)
                }

                bookImageView.setOnClickListener {
                    val action =
                        BooksFragmentDirections.actionBooksFragmentToBookDetailBottomSheet(book)
                    it.findNavController().navigate(action)
                }

                addBasketImage.setOnClickListener {
                    onAddBasketClick(
                        BooksBasketRoomModel(
                            bookName = book.book_name,
                            bookAuthor = book.book_author,
                            bookPublisher = book.book_publisher,
                            bookPrice = book.book_price,
                            bookImageUrl = book.book_image_url
                        )
                    )
                }
            }
        }
    }

    override fun getItemCount(): Int = booksFilterList.size

    fun updateList(list: List<BookModel>) {
        booksList.clear()
        booksList.addAll(list)
        notifyItemRangeChanged(0, list.size)
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
                notifyItemRangeChanged(0, booksFilterList.size)
            }
        }
    }
}