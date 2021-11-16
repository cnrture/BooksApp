package com.canerture.booksapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BookModel(@SerializedName("book_id") @Expose var book_id: Int,
                     @SerializedName("book_name") @Expose var book_name: String,
                     @SerializedName("book_author") @Expose var book_author: String,
                     @SerializedName("book_publisher") @Expose var book_publisher: String,
                     @SerializedName("book_price") @Expose var book_price: String,
                     @SerializedName("book_image_url") @Expose var book_image_url: String,
                     @SerializedName("cart_status") @Expose var cart_status: Int): Serializable