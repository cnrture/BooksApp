package com.canerture.booksapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    @SerializedName("book_id") var bookId: Int? = 0,
    @SerializedName("book_name") var bookName: String?,
    @SerializedName("book_author") var bookAuthor: String?,
    @SerializedName("book_publisher") var bookPublisher: String?,
    @SerializedName("book_price") var bookPrice: String?,
    @SerializedName("book_image_url") var bookImageUrl: String?,
) : Parcelable