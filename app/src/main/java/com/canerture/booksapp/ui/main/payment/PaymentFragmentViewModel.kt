package com.canerture.booksapp.ui.main.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.canerture.booksapp.data.model.BookBasket
import com.canerture.booksapp.data.repos.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PaymentFragmentViewModel @Inject constructor(
    private val booksRepo: BooksRepository
) : ViewModel() {

    private var _booksBasket = MutableLiveData<List<BookBasket>>()
    val booksBasket: LiveData<List<BookBasket>>
        get() = _booksBasket

    init {
        getBooksBasket()
    }

    private fun getBooksBasket() {
        booksRepo.booksBasket()
    }

    fun clearBasket() = booksRepo.clearBasket()

}