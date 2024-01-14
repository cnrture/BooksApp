package com.canerture.booksapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val id: Int? = 0,
    val name: String?,
    val author: String?,
    val publisher: String?,
    val price: String?,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("is_best_seller") val isBestSeller: Boolean?
) : Parcelable