package com.canerture.booksapp.ui.main.books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.booksapp.data.model.Book
import com.canerture.booksapp.data.repos.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val booksRepo: BooksRepository,
) : ViewModel() {

    private var _booksState = MutableLiveData(BooksState(isLoading = true))
    val booksState: LiveData<BooksState>
        get() = _booksState

    fun getBooks() = viewModelScope.launch {
        _booksState.value = booksRepo.books().fold(
            onSuccess = { bookList ->
                BooksState(
                    isLoading = false,
                    booksList = bookList,
                    bestSellersList = bookList.filter { it.isBestSeller == true }
                )
            },
            onFailure = { BooksState(isLoading = false, errorMessage = it.message) }
        )
    }

    fun addBookToBasket(book: Book) = booksRepo.addBookToBasket(book)
}

data class BooksState(
    val isLoading: Boolean = false,
    val booksList: List<Book>? = null,
    val bestSellersList: List<Book>? = null,
    val errorMessage: String? = null,
)