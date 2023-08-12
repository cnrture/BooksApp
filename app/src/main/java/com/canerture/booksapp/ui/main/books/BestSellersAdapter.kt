package com.canerture.booksapp.ui.main.books

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.canerture.booksapp.data.model.Book
import com.canerture.booksapp.databinding.BestSellerItemBinding
import com.squareup.picasso.Picasso

class BestSellersAdapter : RecyclerView.Adapter<BestSellersAdapter.BestSellerItemDesign>() {

    private val bestSellersList = ArrayList<Book>()

    var onBookClick: (Book) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerItemDesign {
        val binding =
            BestSellerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BestSellerItemDesign(binding)
    }

    override fun onBindViewHolder(holder: BestSellerItemDesign, position: Int) {
        holder.bind(bestSellersList[position])
    }

    inner class BestSellerItemDesign(private var binding: BestSellerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {

            with(binding) {

                bookModel = book

                book.imageUrl.let {
                    Picasso.get().load(it).into(bookImageView)
                }

                bookImageView.setOnClickListener {
                    onBookClick(book)
                }
            }
        }
    }

    override fun getItemCount(): Int = bestSellersList.size

    fun updateList(list: List<Book>) {
        bestSellersList.clear()
        bestSellersList.addAll(list)
        notifyItemRangeInserted(0, list.size)
    }
}