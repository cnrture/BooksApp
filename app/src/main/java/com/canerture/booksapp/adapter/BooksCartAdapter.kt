package com.canerture.booksapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.canerture.booksapp.databinding.CartBookCardBinding
import com.canerture.booksapp.model.Books
import com.canerture.booksapp.viewmodel.BooksCartFragmentViewModel
import com.squareup.picasso.Picasso

class BooksCartAdapter(private val cartBooksList: ArrayList<Books>, var viewModel : BooksCartFragmentViewModel):
    RecyclerView.Adapter<BooksCartAdapter.CartBooksCardDesign>() {

    inner class CartBooksCardDesign(cartBookCardBinding: CartBookCardBinding): RecyclerView.ViewHolder(cartBookCardBinding.root) {
        var cartBookCardBinding:CartBookCardBinding
        init {
            this.cartBookCardBinding = cartBookCardBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartBooksCardDesign {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cartBookCardBinding = CartBookCardBinding.inflate(layoutInflater, parent, false)

        return CartBooksCardDesign(cartBookCardBinding)
    }

    override fun onBindViewHolder(holder: CartBooksCardDesign, position: Int) {
        val book = cartBooksList[position]
        holder.cartBookCardBinding.bookObject = book

        Picasso.get().load(book.book_image_url).into(holder.cartBookCardBinding.cartBookImageView)

        holder.cartBookCardBinding.cartDeleteButton.setOnClickListener {
            viewModel.deleteCartBook(book.book_id)
        }
    }

    override fun getItemCount(): Int {
        return cartBooksList.size
    }

}