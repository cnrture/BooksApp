package com.canerture.booksapp.ui.main.books

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.canerture.booksapp.data.model.BookModel
import com.canerture.booksapp.data.model.BooksBasketRoomModel
import com.canerture.booksapp.data.repos.BooksRepository

class BooksFragmentViewModel(context: Context) : ViewModel() {

    private var booksRepo = BooksRepository(context)

    private var _booksList = MutableLiveData<List<BookModel>>()
    val booksList: LiveData<List<BookModel>>
        get() = _booksList

    private var _bestSellersList = MutableLiveData<List<BookModel>>()
    val bestSellersList: LiveData<List<BookModel>>
        get() = _bestSellersList

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private var _isBookAddedBasket = MutableLiveData<Boolean>()
    val isBookAddedBasket: LiveData<Boolean>
        get() = _isBookAddedBasket

    init {
        getBooks()
        getBestSellers()
    }

    private fun getBooks() {
        booksRepo.books()
        _booksList = booksRepo.booksList
        _isLoading = booksRepo.isLoading
    }

    private fun getBestSellers() {
        booksRepo.bestSellers()
        _bestSellersList = booksRepo.bestSellersList
        _isLoading = booksRepo.isLoading
    }

    fun addBookToBasket(bookModel: BooksBasketRoomModel) {
        booksRepo.addBookToBasket(bookModel)
        _isBookAddedBasket = booksRepo.isBookAddedBasket
    }
}