package com.canerture.booksapp.ui.main.books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.canerture.booksapp.data.repos.BooksRepository
import com.canerture.booksapp.data.model.BookModel

class BooksFragmentViewModel : ViewModel() {

    private var booksRepo = BooksRepository()

    private var _booksList = MutableLiveData<List<BookModel>>()
    val booksList: LiveData<List<BookModel>>
        get() = _booksList

    private var _bestSellersList = MutableLiveData<List<BookModel>>()
    val bestSellersList: LiveData<List<BookModel>>
        get() = _bestSellersList

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        getBooks()
        getBestSellers()
    }

    private fun getBooks() {
        booksRepo.books()
        _booksList = booksRepo.getBooksList()
        _isLoading = booksRepo.getIsLoading()
    }

    private fun getBestSellers() {
        booksRepo.bestSellers()
        _bestSellersList = booksRepo.getBestSellersList()
        _isLoading = booksRepo.getIsLoading()
    }

    fun addCartBook(bookId: Int) {
        booksRepo.basketStatusChange(bookId, 1)
    }

}