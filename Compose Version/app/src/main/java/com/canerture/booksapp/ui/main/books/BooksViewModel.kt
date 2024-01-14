package com.canerture.booksapp.ui.main.books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.booksapp.common.Resource
import com.canerture.booksapp.data.model.Book
import com.canerture.booksapp.data.repos.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val booksRepo: BooksRepository
) : ViewModel() {

    private var _booksState = MutableLiveData(BooksState(isLoading = true))
    val booksState: LiveData<BooksState>
        get() = _booksState

    fun getBooks() {
        viewModelScope.launch {
            when (val response = booksRepo.books()) {
                is Resource.Success -> {
                    _booksState.value = BooksState(
                        isLoading = false,
                        booksList = response.data,
                        bestSellersList = response.data.filter { it.isBestSeller == true }
                    )
                }

                is Resource.Fail -> {
                    _booksState.value = BooksState(isLoading = false, failMessage = response.message)
                }

                is Resource.Error -> {
                    _booksState.value = BooksState(isLoading = false, errorMessage = response.throwable.message)
                }
            }
        }
    }

    fun addBookToBasket(book: Book) = booksRepo.addBookToBasket(book)
}

data class BooksState(
    val isLoading: Boolean = false,
    val booksList: List<Book>? = null,
    val bestSellersList: List<Book>? = null,
    val errorMessage: String? = null,
    val failMessage: String? = null
)