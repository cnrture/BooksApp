package com.canerture.booksapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.canerture.booksapp.databinding.BookItemBinding
import com.canerture.booksapp.fragment.BooksFragmentDirections
import com.canerture.booksapp.model.Books
import com.squareup.picasso.Picasso
import kotlin.collections.ArrayList

class BooksItemAdapter : RecyclerView.Adapter<BooksItemAdapter.BookItemDesign>() {

    private val booksList = ArrayList<Books>()

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

        fun bind(book: Books) {

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

            }
        }

    }

    override fun getItemCount(): Int = booksList.size

    fun updateList(list: List<Books>) {
        booksList.clear()
        booksList.addAll(list)
        notifyDataSetChanged()
    }
}