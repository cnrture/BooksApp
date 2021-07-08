package com.canerture.booksapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.canerture.booksapp.repos.BooksRepository
import com.canerture.booksapp.model.Books

class BooksFragmentViewModel: ViewModel() {

    private var mrepo = BooksRepository()
    var books = MutableLiveData<List<Books>>()

    init {
        getBooks()
    }

    fun getBooks() {
        mrepo.getBooks()
        books = mrepo.booksList()
    }

    fun getSearchedBooks(bookPublisher: String) {
        mrepo.getSearchedBooks(bookPublisher)
    }

    fun addBook(bookName: String, bookAuthor: String, bookPublisher: String, bookPrice: String, bookImageUrl: String) {
        mrepo.addBook(bookName, bookAuthor, bookPublisher, bookPrice, bookImageUrl)
    }

    fun addCartBook(bookId: Int) {
        mrepo.cartStatusChange(bookId, 1)
    }

}