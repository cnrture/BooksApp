package com.canerture.booksapp.ui.main.booksbasket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.booksapp.common.Resource
import com.canerture.booksapp.data.model.BookBasket
import com.canerture.booksapp.data.repos.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksBasketFragmentViewModel @Inject constructor(
    private val booksRepo: BooksRepository
) : ViewModel() {

    private var _booksBasketState = MutableLiveData(BooksBasketState(isLoading = true))
    val booksBasketState: LiveData<BooksBasketState>
        get() = _booksBasketState

    fun getBooksBasket() {
        viewModelScope.launch {
            when (val response = booksRepo.booksBasket()) {
                is Resource.Success -> {
                    _booksBasketState.value = BooksBasketState(
                        isLoading = false,
                        booksList = response.data
                    )
                }

                is Resource.Fail -> {
                    _booksBasketState.value = BooksBasketState(isLoading = false, failMessage = response.message)
                }

                is Resource.Error -> {
                    _booksBasketState.value =
                        BooksBasketState(isLoading = false, errorMessage = response.throwable.message)
                }
            }
        }
    }

    fun deleteBookFromBasket(bookId: Int) {
        booksRepo.deleteBookFromBasket(bookId)
        getBooksBasket()
    }
}

data class BooksBasketState(
    val isLoading: Boolean = false,
    val booksList: List<BookBasket>? = null,
    val errorMessage: String? = null,
    val failMessage: String? = null
)