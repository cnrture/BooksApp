package com.canerture.booksapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.canerture.booksapp.repos.BooksRepository
import com.canerture.booksapp.model.Books

class BooksFragmentViewModel : ViewModel() {

    private var booksRepo = BooksRepository()

    private var _books = MutableLiveData<List<Books>>()
    val books: LiveData<List<Books>>
        get() = _books

    init {
        getBooks()
    }

    private fun getBooks() {
        booksRepo.getBooks()
        _books = booksRepo.booksList()
    }

    fun addCartBook(bookId: Int) {
        booksRepo.basketStatusChange(bookId, 1)
    }

}