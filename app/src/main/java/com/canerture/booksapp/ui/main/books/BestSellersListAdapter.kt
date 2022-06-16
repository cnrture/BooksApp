package com.canerture.booksapp.ui.main.books

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.canerture.booksapp.data.model.BookModel
import com.canerture.booksapp.databinding.BestSellersListBinding

class BestSellersListAdapter :
    RecyclerView.Adapter<BestSellersListAdapter.BestSellersListDesign>() {

    private val bestSellersList = ArrayList<BookModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellersListDesign {
        val layoutInflater = LayoutInflater.from(parent.context)
        val bestSellersListBinding = BestSellersListBinding.inflate(layoutInflater, parent, false)
        return BestSellersListDesign(bestSellersListBinding)
    }

    override fun onBindViewHolder(holder: BestSellersListDesign, position: Int) {
        holder.bind(bestSellersList)
    }

    inner class BestSellersListDesign(private var bestSellersListBinding: BestSellersListBinding) :
        RecyclerView.ViewHolder(bestSellersListBinding.root) {

        fun bind(bestSellersList: List<BookModel>) {

            bestSellersListBinding.bestSellersRecycler.apply {
                adapter = BestSellerItemAdapter()
                    .also {
                        it.updateList(bestSellersList)
                    }
            }
        }
    }

    override fun getItemCount(): Int = 1

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<BookModel>) {
        bestSellersList.clear()
        bestSellersList.addAll(list)
        notifyDataSetChanged()
    }
}