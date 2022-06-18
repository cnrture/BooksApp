package com.canerture.booksapp.data.retrofit

import com.canerture.booksapp.data.response.BooksResponse
import retrofit2.Call
import retrofit2.http.GET

interface BooksDAOInterface {

    @GET("/all_books.php")
    fun allBooks(): Call<BooksResponse>

    @GET("/books_basket.php")
    fun booksBasket(): Call<BooksResponse>

    @GET("/best_sellers.php")
    fun bestSellers(): Call<BooksResponse>

}