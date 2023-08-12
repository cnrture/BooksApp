package com.canerture.booksapp.data.repos

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.canerture.booksapp.common.util.ApiUtils
import com.canerture.booksapp.data.model.Book
import com.canerture.booksapp.data.model.BookBasket
import com.canerture.booksapp.data.model.BooksResponse
import com.canerture.booksapp.data.retrofit.BooksService
import com.canerture.booksapp.data.room.BooksBasketDAOInterface
import com.canerture.booksapp.data.room.BooksBasketRoomDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksRepository(context: Context) {

    var booksList = MutableLiveData<List<Book>>()

    var bestSellersList = MutableLiveData<List<Book>>()

    var booksBasketList = MutableLiveData<List<BookBasket>>()

    var isLoading = MutableLiveData<Boolean>()

    var isBookAddedBasket = MutableLiveData<Boolean>()

    private val booksService: BooksService = ApiUtils.getBooksDAOInterface()

    private val booksBasketDAOInterface: BooksBasketDAOInterface? =
        BooksBasketRoomDatabase.booksBasketRoomDatabase(context)?.booksBasketDAOInterface()

    fun books() {
        isLoading.value = true
        booksService.allBooks().enqueue(object : Callback<BooksResponse> {
            override fun onResponse(call: Call<BooksResponse>, response: Response<BooksResponse>) {

                response.body()?.books?.let {
                    booksList.value = it
                    isLoading.value = false
                } ?: kotlin.run {
                    isLoading.value = false
                }

            }

            override fun onFailure(call: Call<BooksResponse>, t: Throwable) {
                Log.e("Books Failure", t.message.orEmpty())
                isLoading.value = false
            }
        })
    }

    fun bestSellers() {
        isLoading.value = true
        booksService.bestSellers().enqueue(object : Callback<BooksResponse> {
            override fun onResponse(call: Call<BooksResponse>, response: Response<BooksResponse>) {

                response.body()?.books?.let {
                    bestSellersList.value = it
                    isLoading.value = false
                } ?: kotlin.run {
                    isLoading.value = false
                }
            }

            override fun onFailure(call: Call<BooksResponse>, t: Throwable) {
                Log.e("Bestsellers Failure", t.message.orEmpty())
                isLoading.value = false
            }
        })
    }

    fun booksBasket() {
        isLoading.value = true

        booksBasketDAOInterface?.getBooksBasket()?.let {
            booksBasketList.value = it
            isLoading.value = false
        } ?: kotlin.run {
            isLoading.value = false
        }
    }

    fun addBookToBasket(book: Book) {

        booksBasketDAOInterface?.getBooksNamesBasket()?.let {

            isBookAddedBasket.value = if (it.contains(book.name).not()) {
                booksBasketDAOInterface.addBookBasket(
                    BookBasket(
                        name = book.name,
                        author = book.author,
                        publisher = book.publisher,
                        price = book.price,
                        imageUrl = book.imageUrl
                    )
                )
                true
            } else {
                false
            }
        }
    }

    fun deleteBookFromBasket(bookId: Int) = booksBasketDAOInterface?.deleteBookWithId(bookId)

    fun clearBasket() = booksBasketDAOInterface?.clearBasket()

}