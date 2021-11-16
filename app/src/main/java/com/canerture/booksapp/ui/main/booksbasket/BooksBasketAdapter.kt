package com.canerture.booksapp.ui.main.booksbasket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.canerture.booksapp.databinding.BookBasketItemBinding
import com.canerture.booksapp.data.model.BookModel
import com.squareup.picasso.Picasso

class BooksBasketAdapter(var viewModel: BooksBasketFragmentViewModel) :
    RecyclerView.Adapter<BooksBasketAdapter.BookBasketItemDesign>() {

    private val cartBooksList = ArrayList<BookModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookBasketItemDesign {
        val layoutInflater = LayoutInflater.from(parent.context)
        val bookBasketItemBinding = BookBasketItemBinding.inflate(layoutInflater, parent, false)
        return BookBasketItemDesign(bookBasketItemBinding)
    }

    override fun onBindViewHolder(holder: BookBasketItemDesign, position: Int) {
        holder.bind(cartBooksList[position])
    }

    inner class BookBasketItemDesign(private var bookBasketItemBinding: BookBasketItemBinding) :
        RecyclerView.ViewHolder(bookBasketItemBinding.root) {

        fun bind(bookBasket: BookModel) {

            bookBasketItemBinding.apply {

                bookModel = bookBasket

                bookBasket.book_image_url.let {
                    Picasso.get().load(bookBasket.book_image_url).into(cartBookImageView)
                }

                cartDeleteButton.setOnClickListener {
                    viewModel.deleteBasketBook(bookBasket.book_id)
                }

            }
        }

    }

    override fun getItemCount(): Int {
        return cartBooksList.size
    }

    fun updateList(list: List<BookModel>) {
        cartBooksList.clear()
        cartBooksList.addAll(list)
        notifyDataSetChanged()
    }

}