package com.canerture.booksapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.canerture.booksapp.R
import com.canerture.booksapp.databinding.BookCardBinding
import com.canerture.booksapp.model.Books
import com.squareup.picasso.Picasso

class BooksAdapter(private val booksList: List<Books>, private val clickedBookListener: ClickedBookListener):
    RecyclerView.Adapter<BooksAdapter.BookCardDesign>() {

    inner class BookCardDesign(bookCardBinding: BookCardBinding): RecyclerView.ViewHolder(bookCardBinding.root) {
        var bookCardBinding:BookCardBinding
        init {
            this.bookCardBinding = bookCardBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookCardDesign {
        val layoutInflater = LayoutInflater.from(parent.context)
        val bookCardBinding = BookCardBinding.inflate(layoutInflater, parent, false)

        return BookCardDesign(bookCardBinding)
    }

    override fun onBindViewHolder(holder: BookCardDesign, position: Int) {
        val book = booksList[position]
        holder.bookCardBinding.bookObject = book

        val defaultImage: Int = R.drawable.cart

        if (book.book_image_url.isEmpty()) {
            Picasso.get().load(defaultImage).into(holder.bookCardBinding.bookImageView)
        } else{
            Picasso.get().load(book.book_image_url).into(holder.bookCardBinding.bookImageView)
        }

        holder.bookCardBinding.bookImageView.setOnClickListener {
            clickedBookListener.onClickedBookListener(book, position)
        }
    }

    override fun getItemCount(): Int {
        return booksList.size
    }

    interface ClickedBookListener {
        fun onClickedBookListener(data: Books, pos: Int)
    }


}