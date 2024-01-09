package com.canerture.booksapp.data.model

import com.google.gson.annotations.SerializedName

data class BooksResponse(
    @SerializedName("books") var books: List<Book>?,
    @SerializedName("success") var success: Int?,
)