package com.canerture.booksapp.data.repos

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.canerture.booksapp.common.util.ApiUtils
import com.canerture.booksapp.data.model.BookModel
import com.canerture.booksapp.data.model.BooksBasketRoomModel
import com.canerture.booksapp.data.response.BooksResponse
import com.canerture.booksapp.data.retrofit.BooksDAOInterface
import com.canerture.booksapp.data.room.BooksBasketDAOInterface
import com.canerture.booksapp.data.room.BooksBasketRoomDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksRepository(context: Context) {

    var booksList = MutableLiveData<List<BookModel>>()

    var bestSellersList = MutableLiveData<List<BookModel>>()

    var booksBasketList = MutableLiveData<List<BooksBasketRoomModel>>()

    var isLoading = MutableLiveData<Boolean>()

    var isBookAddedBasket = MutableLiveData<Boolean>()

    private val booksDIF: BooksDAOInterface = ApiUtils.getBooksDAOInterface()

    private val booksBasketDAOInterface: BooksBasketDAOInterface? =
        BooksBasketRoomDatabase.booksBasketRoomDatabase(context)?.booksBasketDAOInterface()

    fun books() {
        isLoading.value = true
        booksDIF.allBooks().enqueue(object : Callback<BooksResponse> {
            override fun onResponse(call: Call<BooksResponse>, response: Response<BooksResponse>) {

                response.body()?.books?.let {
                    booksList.value = it
                    isLoading.value = false
                } ?: run {
                    isLoading.value = false
                }

            }

            override fun onFailure(call: Call<BooksResponse>, t: Throwable) {
                t.localizedMessage?.toString()?.let { Log.e("Books Failure", it) }
                isLoading.value = false
            }
        })
    }

    fun bestSellers() {
        isLoading.value = true
        booksDIF.bestSellers().enqueue(object : Callback<BooksResponse> {
            override fun onResponse(call: Call<BooksResponse>, response: Response<BooksResponse>) {

                response.body()?.books?.let {
                    bestSellersList.value = it
                    isLoading.value = false
                } ?: run {
                    isLoading.value = false
                }
            }

            override fun onFailure(call: Call<BooksResponse>, t: Throwable) {
                t.localizedMessage?.toString()?.let { Log.e("Bestsellers Failure", it) }
                isLoading.value = false
            }
        })
    }

    fun booksBasket() {
        isLoading.value = true

        booksBasketDAOInterface?.getBooksBasket()?.let {
            booksBasketList.value = it
            isLoading.value = false
        } ?: run {
            isLoading.value = false
        }
    }

    fun addBookToBasket(bookModel: BooksBasketRoomModel) {

        booksBasketDAOInterface?.getBooksNamesBasket()?.let {

            isBookAddedBasket.value = if (it.contains(bookModel.bookName).not()) {
                booksBasketDAOInterface.addBookBasket(bookModel)
                true
            } else {
                false
            }
        }
    }

    fun deleteBookFromBasket(bookId: Int) {
        booksBasketDAOInterface?.deleteBookWithId(bookId)
    }

    fun clearBasket() {
        booksBasketDAOInterface?.clearBasket()
    }

}