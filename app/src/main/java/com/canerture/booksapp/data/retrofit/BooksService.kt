package com.canerture.booksapp.data.retrofit

import com.canerture.booksapp.data.model.BooksResponse
import retrofit2.Call
import retrofit2.http.GET

interface BooksService {

    @GET("all_books.php")
    fun allBooks(): Call<BooksResponse>

    @GET("best_sellers.php")
    fun bestSellers(): Call<BooksResponse>

}