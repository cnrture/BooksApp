package com.canerture.booksapp.ui.main.booksbasket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.canerture.booksapp.data.repos.BooksRepository
import com.canerture.booksapp.data.model.BookModel

class BooksBasketFragmentViewModel : ViewModel() {

    private val booksRepo = BooksRepository()

    private var _booksBasket = MutableLiveData<List<BookModel>>()
    val booksBasket: LiveData<List<BookModel>>
        get() = _booksBasket

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        getBooksBasket()
    }

    private fun getBooksBasket() {
        booksRepo.booksBasket()
        _booksBasket = booksRepo.getBooksBasketList()
        _isLoading = booksRepo.getIsLoading()
    }

    fun deleteBasketBook(bookId: Int) {
        booksRepo.basketStatusChange(bookId, 0)
        getBooksBasket()
    }

}