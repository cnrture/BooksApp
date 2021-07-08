package com.canerture.booksapp.repos

import androidx.lifecycle.MutableLiveData
import com.canerture.booksapp.model.Books
import com.canerture.booksapp.response.BooksResponse
import com.canerture.booksapp.response.CRUDResponse
import com.canerture.booksapp.retrofit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksRepository {

    private var booksList = MutableLiveData<List<Books>>()
    private var cartBooksList = MutableLiveData<ArrayList<Books>>()

    private val booksDIF : BooksDAOInterface = ApiUtils.getBooksDAOInterface()

    init {
        booksList = MutableLiveData()
        cartBooksList = MutableLiveData()
    }

    fun booksList() : MutableLiveData<List<Books>>{
        return booksList
    }

    fun cartBooksList() : MutableLiveData<ArrayList<Books>>{
        return cartBooksList
    }

    fun getBooks() {
        booksDIF.allBooks().enqueue(object : Callback<BooksResponse> {
            override fun onResponse(call: Call<BooksResponse>?, response: Response<BooksResponse>) {
                val books = response.body()!!.books
                booksList.value = books
            }
            override fun onFailure(call: Call<BooksResponse>, t: Throwable) {
                println(t.localizedMessage?.toString())
            }
        })
    }

    fun getCartBooks() {
        booksDIF.allBooks().enqueue(object : Callback<BooksResponse> {
            override fun onResponse(call: Call<BooksResponse>?, response: Response<BooksResponse>) {
                val books = response.body()!!.books
                val cartBooks = arrayListOf<Books>()
                println(books)
                for (book in books) {
                    if (book.cart_status == 1) {
                        cartBooks.add(book)
                    }
                }
                cartBooksList.value = cartBooks
            }
            override fun onFailure(call: Call<BooksResponse>, t: Throwable) {
                println(t.localizedMessage?.toString())
            }
        })
    }

    fun getSearchedBooks(bookName: String) {
        booksDIF.searchBook(bookName).enqueue(object : Callback<BooksResponse> {
            override fun onResponse(call: Call<BooksResponse>?, response: Response<BooksResponse>) {
                val books = response.body()!!.books
                booksList.value = books
            }

            override fun onFailure(call: Call<BooksResponse>, t: Throwable) {
                println(t.localizedMessage?.toString())
            }
        })
    }

    fun addBook(bookName: String, bookAuthor: String, bookPublisher: String, bookPrice: String, bookImageUrl: String) {
        booksDIF.addBook(bookName, bookAuthor, bookPublisher, bookPrice, bookImageUrl).enqueue(object : Callback<CRUDResponse> {
            override fun onResponse(call: Call<CRUDResponse>, response: Response<CRUDResponse>) {

            }
            override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {
                println(t.localizedMessage?.toString())
            }
        })
    }

    fun cartStatusChange(bookId: Int, basketStatus: Int) {
        booksDIF.cartStatusChange(bookId, basketStatus).enqueue(object: Callback<CRUDResponse> {
            override fun onResponse(call: Call<CRUDResponse>, response: Response<CRUDResponse>) {

            }

            override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {
                println(t.localizedMessage?.toString())
            }

        })
    }

}