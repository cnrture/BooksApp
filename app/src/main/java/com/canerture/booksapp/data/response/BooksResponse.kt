package com.canerture.booksapp.data.response

import com.canerture.booksapp.data.model.BookModel
import com.google.gson.annotations.SerializedName

data class BooksResponse(
    @SerializedName("books") var books: List<BookModel>,
    @SerializedName("success") var success: Int
)