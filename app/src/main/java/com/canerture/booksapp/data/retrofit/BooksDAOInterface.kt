package com.canerture.booksapp.data.retrofit

import com.canerture.booksapp.data.response.BooksResponse
import com.canerture.booksapp.data.response.CRUDResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface BooksDAOInterface {

    @GET("/all_books.php")
    fun allBooks(): Call<BooksResponse>

    @GET("/books_basket.php")
    fun booksBasket(): Call<BooksResponse>

    @GET("/best_sellers.php")
    fun bestSellers(): Call<BooksResponse>

    @POST("/basket_status_change.php")
    @FormUrlEncoded
    fun basketStatusChange(
        @Field("book_id") bookId: Int,
        @Field("basket_status") basketStatus: Int
    ): Call<CRUDResponse>

}