package com.canerture.booksapp.retrofit

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