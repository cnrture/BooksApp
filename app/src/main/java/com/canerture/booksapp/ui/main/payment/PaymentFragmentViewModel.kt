package com.canerture.booksapp.ui.main.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.canerture.booksapp.data.model.BookModel
import com.canerture.booksapp.data.repos.BooksRepository

class PaymentFragmentViewModel : ViewModel() {

    private val booksRepo = BooksRepository()

    private var _booksBasket = MutableLiveData<List<BookModel>>()
    val booksBasket: LiveData<List<BookModel>>
        get() = _booksBasket

    init {
        getBooksBasket()
    }

    private fun getBooksBasket() {
        booksRepo.booksBasket()
        _booksBasket = booksRepo.getBooksBasketList()
    }

}