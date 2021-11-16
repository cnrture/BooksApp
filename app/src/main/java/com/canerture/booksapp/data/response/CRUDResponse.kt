package com.canerture.booksapp.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CRUDResponse(
    @SerializedName("success") @Expose var success: Int,
    @SerializedName("message") @Expose var message: String
)