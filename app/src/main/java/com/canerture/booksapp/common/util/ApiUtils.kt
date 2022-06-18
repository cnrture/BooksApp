package com.canerture.booksapp.common.util

import com.canerture.booksapp.common.Constants.BASE_URL
import com.canerture.booksapp.data.retrofit.BooksDAOInterface
import com.canerture.booksapp.data.retrofit.RetrofitClient

class ApiUtils {

    companion object {

        fun getBooksDAOInterface(): BooksDAOInterface {
            return RetrofitClient.getClient(BASE_URL).create(BooksDAOInterface::class.java)
        }
    }
}