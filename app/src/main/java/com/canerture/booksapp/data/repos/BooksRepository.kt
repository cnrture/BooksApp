package com.canerture.booksapp.data.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.canerture.booksapp.data.model.BookModel
import com.canerture.booksapp.data.response.BooksResponse
import com.canerture.booksapp.data.response.CRUDResponse
import com.canerture.booksapp.data.retrofit.*
import com.canerture.booksapp.data.util.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksRepository {

    private var _booksList = MutableLiveData<List<BookModel>>()
    val booksList: LiveData<List<BookModel>>
        get() = _booksList

    private var _bestSellersList = MutableLiveData<List<BookModel>>()
    val bestSellersList: LiveData<List<BookModel>>
        get() = _bestSellersList

    private var _booksBasketList = MutableLiveData<List<BookModel>>()
    val bookModelBasketList: LiveData<List<BookModel>>
        get() = _booksBasketList

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val booksDIF: BooksDAOInterface = ApiUtils.getBooksDAOInterface()

    fun getBooksList(): MutableLiveData<List<BookModel>> {
        return _booksList
    }

    fun getBestSellersList(): MutableLiveData<List<BookModel>> {
        return _bestSellersList
    }

    fun getBooksBasketList(): MutableLiveData<List<BookModel>> {
        return _booksBasketList
    }

    fun getIsLoading(): MutableLiveData<Boolean> {
        return _isLoading
    }

    fun books() {
        _isLoading.value = true
        booksDIF.allBooks().enqueue(object : Callback<BooksResponse> {
            override fun onResponse(call: Call<BooksResponse>, response: Response<BooksResponse>) {
                val books = response.body()?.books
                books?.let {
                    _booksList.value = it
                    _isLoading.value = false
                } ?: kotlin.run {
                    _isLoading.value = false
                }
            }

            override fun onFailure(call: Call<BooksResponse>, t: Throwable) {
                println(t.localizedMessage?.toString())
                _isLoading.value = false
            }
        })
    }

    fun bestSellers() {
        _isLoading.value = true
        booksDIF.bestSellers().enqueue(object : Callback<BooksResponse> {
            override fun onResponse(call: Call<BooksResponse>, response: Response<BooksResponse>) {
                val bestSellers = response.body()?.books
                bestSellers?.let {
                    _bestSellersList.value = it
                    _isLoading.value = false
                } ?: kotlin.run {
                    _isLoading.value = false
                }
            }

            override fun onFailure(call: Call<BooksResponse>, t: Throwable) {
                println(t.localizedMessage?.toString())
                _isLoading.value = false
            }

        })
    }

    fun booksBasket() {
        _isLoading.value = true
        booksDIF.booksBasket().enqueue(object : Callback<BooksResponse> {
            override fun onResponse(call: Call<BooksResponse>?, response: Response<BooksResponse>) {
                val cartBooks = response.body()?.books
                cartBooks?.let {
                    _booksBasketList.value = it
                    _isLoading.value = false
                } ?: kotlin.run {
                    _isLoading.value = false
                }
            }

            override fun onFailure(call: Call<BooksResponse>, t: Throwable) {
                println(t.localizedMessage?.toString())
                _isLoading.value = false
            }
        })
    }

    fun basketStatusChange(bookId: Int, basketStatus: Int) {
        booksDIF.basketStatusChange(bookId, basketStatus).enqueue(object : Callback<CRUDResponse> {
            override fun onResponse(call: Call<CRUDResponse>, response: Response<CRUDResponse>) {

            }

            override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {
                println(t.localizedMessage?.toString())
            }

        })
    }

}