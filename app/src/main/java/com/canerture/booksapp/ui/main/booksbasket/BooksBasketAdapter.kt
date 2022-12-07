package com.canerture.booksapp.ui.main.booksbasket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.canerture.booksapp.data.model.Book
import com.canerture.booksapp.data.model.BookBasket
import com.canerture.booksapp.databinding.BookBasketItemBinding
import com.squareup.picasso.Picasso

class BooksBasketAdapter : RecyclerView.Adapter<BooksBasketAdapter.BookBasketItemDesign>() {

    private val booksBasketList = ArrayList<BookBasket>()

    var onRemoveBasketClick: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookBasketItemDesign {
        val binding =
            BookBasketItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookBasketItemDesign(binding)
    }

    override fun onBindViewHolder(holder: BookBasketItemDesign, position: Int) =
        holder.bind(booksBasketList[position])

    inner class BookBasketItemDesign(private var binding: BookBasketItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(bookBasket: BookBasket) {

            with(binding) {

                bookModel = bookBasket

                bookBasket.bookImageUrl?.let {
                    Picasso.get().load(it).into(bookBasketImageView)
                }

                bookBasketDelete.setOnClickListener {
                    onRemoveBasketClick(bookBasket.bookId)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return booksBasketList.size
    }

    fun updateList(list: List<BookBasket>) {
        booksBasketList.clear()
        booksBasketList.addAll(list)
        notifyItemRangeInserted(0, list.size)
    }
}