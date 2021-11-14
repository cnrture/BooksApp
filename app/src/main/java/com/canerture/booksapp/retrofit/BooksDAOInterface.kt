package com.canerture.booksapp.retrofit

import com.canerture.booksapp.response.BooksResponse
import com.canerture.booksapp.response.CRUDResponse
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

    @POST("/basket_status_change.php")
    @FormUrlEncoded
    fun basketStatusChange(
        @Field("book_id") bookId: Int,
        @Field("basket_status") basketStatus: Int
    ): Call<CRUDResponse>

}