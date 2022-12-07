package com.canerture.booksapp.common.util

import com.canerture.booksapp.common.Constants.BASE_URL
import com.canerture.booksapp.data.retrofit.BooksService
import com.canerture.booksapp.data.retrofit.RetrofitClient

object ApiUtils {
    fun getBooksDAOInterface(): BooksService =
        RetrofitClient.getClient(BASE_URL).create(BooksService::class.java)
}