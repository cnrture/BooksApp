package com.canerture.booksapp.ui.main.payment

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.canerture.booksapp.data.model.BooksBasketRoomModel
import com.canerture.booksapp.data.repos.BooksRepository

class PaymentFragmentViewModel(context: Context) : ViewModel() {

    private val booksRepo = BooksRepository(context)

    private var _booksBasket = MutableLiveData<List<BooksBasketRoomModel>>()
    val booksBasket: LiveData<List<BooksBasketRoomModel>>
        get() = _booksBasket

    init {
        getBooksBasket()
    }

    private fun getBooksBasket() {
        booksRepo.booksBasket()
        _booksBasket = booksRepo.booksBasketList
    }

    fun clearBasket() {
        booksRepo.clearBasket()
    }

}