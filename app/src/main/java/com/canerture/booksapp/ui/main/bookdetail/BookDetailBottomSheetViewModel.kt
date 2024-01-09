package com.canerture.booksapp.ui.main.bookdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.canerture.booksapp.data.model.Book
import com.canerture.booksapp.data.repos.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookDetailBottomSheetViewModel @Inject constructor(
    private val booksRepo: BooksRepository,
) : ViewModel() {

    private var _isBookAddedBasket = MutableLiveData<Boolean>()
    val isBookAddedBasket: LiveData<Boolean>
        get() = _isBookAddedBasket

    fun addBookToBasket(book: Book) = booksRepo.addBookToBasket(book)
}