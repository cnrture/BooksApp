package com.canerture.booksapp.ui.main.books

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.canerture.booksapp.data.model.Book
import com.canerture.booksapp.data.repos.BooksRepository

class BooksFragmentViewModel(context: Context) : ViewModel() {

    private var booksRepo = BooksRepository(context)

    private var _booksList = MutableLiveData<List<Book>>()
    val booksList: LiveData<List<Book>>
        get() = _booksList

    private var _bestSellersList = MutableLiveData<List<Book>>()
    val bestSellersList: LiveData<List<Book>>
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

    fun addBookToBasket(book: Book) {
        booksRepo.addBookToBasket(book)
        _isBookAddedBasket = booksRepo.isBookAddedBasket
    }
}