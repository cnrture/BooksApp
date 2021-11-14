package com.canerture.booksapp.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.canerture.booksapp.model.Books
import com.canerture.booksapp.response.BooksResponse
import com.canerture.booksapp.response.CRUDResponse
import com.canerture.booksapp.retrofit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksRepository {

    private var _booksList = MutableLiveData<List<Books>>()
    val booksList: LiveData<List<Books>>
        get() = _booksList

    private var _booksBasketList = MutableLiveData<List<Books>>()
    val booksBasketList: LiveData<List<Books>>
        get() = _booksBasketList

    private val booksDIF: BooksDAOInterface = ApiUtils.getBooksDAOInterface()

    fun booksList(): MutableLiveData<List<Books>> {
        return _booksList
    }

    fun booksBasketList(): MutableLiveData<List<Books>> {
        return _booksBasketList
    }

    fun getBooks() {
        booksDIF.allBooks().enqueue(object : Callback<BooksResponse> {
            override fun onResponse(call: Call<BooksResponse>, response: Response<BooksResponse>) {
                val books = response.body()?.books
                books?.let {
                    _booksList.value = it
                }
            }

            override fun onFailure(call: Call<BooksResponse>, t: Throwable) {
                println(t.localizedMessage?.toString())
            }
        })
    }

    fun getBooksBasket() {
        booksDIF.booksBasket().enqueue(object : Callback<BooksResponse> {
            override fun onResponse(call: Call<BooksResponse>?, response: Response<BooksResponse>) {
                val cartBooks = response.body()?.books
                cartBooks?.let {
                    _booksBasketList.value = it
                }
            }

            override fun onFailure(call: Call<BooksResponse>, t: Throwable) {
                println(t.localizedMessage?.toString())
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