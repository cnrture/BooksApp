package com.canerture.booksapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.canerture.booksapp.repos.BooksRepository
import com.canerture.booksapp.model.Books

class BooksBasketFragmentViewModel : ViewModel() {

    private val booksRepo = BooksRepository()

    private var _booksBasket = MutableLiveData<List<Books>>()
    val booksBasket: LiveData<List<Books>>
        get() = _booksBasket

    init {
        getBooksBasket()
    }

    private fun getBooksBasket() {
        booksRepo.getBooksBasket()
        _booksBasket = booksRepo.booksBasketList()
    }

    fun deleteBasketBook(bookId: Int) {
        booksRepo.basketStatusChange(bookId, 0)
        booksRepo.getBooksBasket()
    }

}