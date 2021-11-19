package com.canerture.booksapp.data.util

import com.canerture.booksapp.data.retrofit.BooksDAOInterface
import com.canerture.booksapp.data.retrofit.RetrofitClient
import com.canerture.booksapp.data.retrofit.UsersDAOInterface

class ApiUtils {

    companion object {
        private const val BASE_URL = "http://books.canerture.com/"

        fun getBooksDAOInterface(): BooksDAOInterface {
            return RetrofitClient.getClient(BASE_URL).create(BooksDAOInterface::class.java)
        }

        fun getUserDAOInterface(): UsersDAOInterface {
            return RetrofitClient.getClient(BASE_URL).create(UsersDAOInterface::class.java)
        }
    }

}