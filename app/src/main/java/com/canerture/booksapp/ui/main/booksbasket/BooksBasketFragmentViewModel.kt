package com.canerture.booksapp.ui.main.booksbasket

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.canerture.booksapp.data.model.BooksBasketRoomModel
import com.canerture.booksapp.data.repos.BooksRepository

class BooksBasketFragmentViewModel(context: Context) : ViewModel() {

    private val booksRepo = BooksRepository(context)

    private var _booksBasket = MutableLiveData<List<BooksBasketRoomModel>>()
    val booksBasket: LiveData<List<BooksBasketRoomModel>>
        get() = _booksBasket

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        getBooksBasket()
    }

    private fun getBooksBasket() {
        booksRepo.booksBasket()
        _booksBasket = booksRepo.booksBasketList
        _isLoading = booksRepo.isLoading
    }

    fun deleteBookFromBasket(bookId: Int) {
        booksRepo.deleteBookFromBasket(bookId)
        getBooksBasket()
    }
}