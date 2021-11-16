package com.canerture.booksapp.ui.main.books

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.canerture.booksapp.R
import com.canerture.booksapp.databinding.BookItemBinding
import com.canerture.booksapp.data.model.BookModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlin.collections.ArrayList

class BooksItemAdapter : RecyclerView.Adapter<BooksItemAdapter.BookItemDesign>() {

    private val booksList = ArrayList<BookModel>()
    var onAddBasketClick: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemDesign {
        val layoutInflater = LayoutInflater.from(parent.context)
        val bookItemBinding = BookItemBinding.inflate(layoutInflater, parent, false)
        return BookItemDesign(bookItemBinding)
    }

    override fun onBindViewHolder(holder: BookItemDesign, position: Int) {
        holder.bind(booksList[position])
    }

    inner class BookItemDesign(private var bookItemBinding: BookItemBinding) :
        RecyclerView.ViewHolder(bookItemBinding.root) {

        fun bind(book: BookModel) {

            bookItemBinding.apply {

                bookModel = book

                book.book_image_url.let {
                    Picasso.get().load(it).into(bookImageView)
                }

                bookImageView.setOnClickListener {
                    val action =
                        BooksFragmentDirections.actionBooksFragmentToBookDetailBottomSheet(book)
                    it.findNavController().navigate(action)
                }

                addBasketImage.setOnClickListener {
                    onAddBasketClick(book.book_id)
                    Snackbar.make(it, R.string.add_cart_snack_text, 1000).show()
                }

            }
        }

    }

    override fun getItemCount(): Int = booksList.size

    fun updateList(list: List<BookModel>) {
        booksList.clear()
        booksList.addAll(list)
        notifyDataSetChanged()
    }
}