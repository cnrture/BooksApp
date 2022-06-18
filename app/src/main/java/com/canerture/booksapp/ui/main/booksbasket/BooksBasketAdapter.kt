package com.canerture.booksapp.ui.main.booksbasket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.canerture.booksapp.data.model.BooksBasketRoomModel
import com.canerture.booksapp.databinding.BookBasketItemBinding
import com.squareup.picasso.Picasso

class BooksBasketAdapter : RecyclerView.Adapter<BooksBasketAdapter.BookBasketItemDesign>() {

    private val booksBasketList = ArrayList<BooksBasketRoomModel>()
    var onRemoveBasketClick: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookBasketItemDesign {
        val bookBasketItemBinding =
            BookBasketItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookBasketItemDesign(bookBasketItemBinding)
    }

    override fun onBindViewHolder(holder: BookBasketItemDesign, position: Int) {
        holder.bind(booksBasketList[position])
    }

    inner class BookBasketItemDesign(private var bookBasketItemBinding: BookBasketItemBinding) :
        RecyclerView.ViewHolder(bookBasketItemBinding.root) {

        fun bind(bookBasket: BooksBasketRoomModel) {

            bookBasketItemBinding.apply {

                bookModel = bookBasket

                bookBasket.bookImageUrl.let {
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

    fun updateList(list: List<BooksBasketRoomModel>) {
        booksBasketList.clear()
        booksBasketList.addAll(list)
        notifyDataSetChanged()
    }
}