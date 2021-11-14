package com.canerture.booksapp.response

import com.canerture.booksapp.model.Books
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BooksResponse(
    @SerializedName("books") @Expose var books: List<Books>,
    @SerializedName("success") @Expose var success: Int
)