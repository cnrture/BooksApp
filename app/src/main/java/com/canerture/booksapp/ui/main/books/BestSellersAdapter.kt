package com.canerture.booksapp.ui.main.books

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.canerture.booksapp.data.model.BookModel
import com.canerture.booksapp.databinding.BestSellerItemBinding
import com.squareup.picasso.Picasso

class BestSellersAdapter : RecyclerView.Adapter<BestSellersAdapter.BestSellerItemDesign>() {

    private val bestSellersList = ArrayList<BookModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerItemDesign {
        val bestSellerItemBinding =
            BestSellerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BestSellerItemDesign(bestSellerItemBinding)
    }

    override fun onBindViewHolder(holder: BestSellerItemDesign, position: Int) {
        holder.bind(bestSellersList[position])
    }

    inner class BestSellerItemDesign(private var bestSellerItemBinding: BestSellerItemBinding) :
        RecyclerView.ViewHolder(bestSellerItemBinding.root) {

        fun bind(book: BookModel) {

            bestSellerItemBinding.apply {

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

    override fun getItemCount(): Int = bestSellersList.size

    fun updateList(list: List<BookModel>) {
        bestSellersList.clear()
        bestSellersList.addAll(list)
        notifyDataSetChanged()
    }
}