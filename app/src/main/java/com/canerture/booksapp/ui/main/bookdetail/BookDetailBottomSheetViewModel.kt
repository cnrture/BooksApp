package com.canerture.booksapp.ui.main.bookdetail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.canerture.booksapp.data.model.BooksBasketRoomModel
import com.canerture.booksapp.data.repos.BooksRepository

class BookDetailBottomSheetViewModel(context: Context) : ViewModel() {

    private var booksRepo = BooksRepository(context)

    private var _isBookAddedBasket = MutableLiveData<Boolean>()
    val isBookAddedBasket: LiveData<Boolean>
        get() = _isBookAddedBasket

    init {
        _isBookAddedBasket = booksRepo.isBookAddedBasket
    }

    fun addBookToBasket(bookModel: BooksBasketRoomModel) {
        booksRepo.addBookToBasket(bookModel)
    }
}