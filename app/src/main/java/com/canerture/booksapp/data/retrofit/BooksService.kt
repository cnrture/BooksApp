package com.canerture.booksapp.data.retrofit

import com.canerture.booksapp.common.Constants.BEST_SELLERS
import com.canerture.booksapp.common.Constants.BOOKS
import com.canerture.booksapp.data.model.BooksResponse
import retrofit2.Response
import retrofit2.http.GET

interface BooksService {

    @GET(BOOKS)
    suspend fun allBooks(): Response<BooksResponse>

    @GET(BEST_SELLERS)
    suspend fun bestSellers(): Response<BooksResponse>
}