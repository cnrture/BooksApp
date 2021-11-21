package com.canerture.booksapp.data.repos

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.canerture.booksapp.data.model.BookModel
import com.canerture.booksapp.data.model.BooksBasketRoomModel
import com.canerture.booksapp.data.response.BooksResponse
import com.canerture.booksapp.data.response.CRUDResponse
import com.canerture.booksapp.data.retrofit.*
import com.canerture.booksapp.data.room.BooksBasketDAOInterface
import com.canerture.booksapp.data.room.BooksBasketRoomDatabase
import com.canerture.booksapp.data.util.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksRepository(context: Context) {

    private var _booksList = MutableLiveData<List<BookModel>>()
    val booksList: LiveData<List<BookModel>>
        get() = _booksList

    private var _bestSellersList = MutableLiveData<List<BookModel>>()
    val bestSellersList: LiveData<List<BookModel>>
        get() = _bestSellersList

    private var _booksBasketList = MutableLiveData<List<BooksBasketRoomModel>>()
    val bookModelBasketList: LiveData<List<BooksBasketRoomModel>>
        get() = _booksBasketList

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private var _isBookAddBasket = MutableLiveData<Boolean>()
    val isBookAddBasket: LiveData<Boolean>
        get() = _isBookAddBasket

    private val booksDIF: BooksDAOInterface = ApiUtils.getBooksDAOInterface()

    private val booksBasketDAOInterface: BooksBasketDAOInterface? =
        BooksBasketRoomDatabase.booksBasketRoomDatabase(context)?.booksBasketDAOInterface()

    fun getBooksList(): MutableLiveData<List<BookModel>> {
        return _booksList
    }

    fun getBestSellersList(): MutableLiveData<List<BookModel>> {
        return _bestSellersList
    }

    fun getBooksBasketList(): MutableLiveData<List<BooksBasketRoomModel>> {
        return _booksBasketList
    }

    fun getIsLoading(): MutableLiveData<Boolean> {
        return _isLoading
    }

    fun getIsBookAddBasket(): MutableLiveData<Boolean> {
        return _isBookAddBasket
    }

    fun books() {
        _isLoading.value = true
        booksDIF.allBooks().enqueue(object : Callback<BooksResponse> {
            override fun onResponse(call: Call<BooksResponse>, response: Response<BooksResponse>) {

                response.body()?.books?.let {
                    _booksList.value = it
                    _isLoading.value = false
                } ?: run {
                    _isLoading.value = false
                }

            }

            override fun onFailure(call: Call<BooksResponse>, t: Throwable) {
                t.localizedMessage?.toString()?.let { Log.e("Books Failure", it) }
                _isLoading.value = false
            }
        })
    }

    fun bestSellers() {
        _isLoading.value = true
        booksDIF.bestSellers().enqueue(object : Callback<BooksResponse> {
            override fun onResponse(call: Call<BooksResponse>, response: Response<BooksResponse>) {

                response.body()?.books?.let {
                    _bestSellersList.value = it
                    _isLoading.value = false
                } ?: run {
                    _isLoading.value = false
                }

            }

            override fun onFailure(call: Call<BooksResponse>, t: Throwable) {
                t.localizedMessage?.toString()?.let { Log.e("Bestsellers Failure", it) }
                _isLoading.value = false
            }

        })
    }

    fun booksBasket() {
        _isLoading.value = true

        booksBasketDAOInterface?.getBooksBasket()?.let {
            _booksBasketList.value = it
            _isLoading.value = false
        } ?: run {
            _isLoading.value = false
        }
    }

    fun addBookToBasket(bookModel: BooksBasketRoomModel): Boolean {
        var isBookAddBasket = false
        booksBasketDAOInterface?.getBooksNamesBasket()?.let {
            isBookAddBasket = if (it.contains(bookModel.bookName).not()) {
                booksBasketDAOInterface.addBookBasket(bookModel)
                true
            } else {
                false
            }
        }
        return isBookAddBasket
    }

    fun deleteBookFromBasket(bookId: Int) {
        booksBasketDAOInterface?.deleteBookWithId(bookId)
    }

    fun clearBasket() {
        booksBasketDAOInterface?.clearBasket()
    }

}