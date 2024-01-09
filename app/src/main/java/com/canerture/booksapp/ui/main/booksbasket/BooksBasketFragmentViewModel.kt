package com.canerture.booksapp.ui.main.booksbasket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.canerture.booksapp.data.model.BookBasket
import com.canerture.booksapp.data.repos.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BooksBasketFragmentViewModel @Inject constructor(
    private val booksRepo: BooksRepository
) : ViewModel() {

    private var _booksBasket = MutableLiveData<List<BookBasket>>()
    val booksBasket: LiveData<List<BookBasket>>
        get() = _booksBasket

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        getBooksBasket()
    }

    private fun getBooksBasket() {
        booksRepo.booksBasket()
    }

    fun deleteBookFromBasket(bookId: Int) {
        booksRepo.deleteBookFromBasket(bookId)
        getBooksBasket()
    }
}