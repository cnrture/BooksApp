package com.canerture.booksapp.data.response

import com.canerture.booksapp.data.model.BookModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BooksResponse(
    @SerializedName("books") @Expose var books: List<BookModel>,
    @SerializedName("success") @Expose var success: Int
)