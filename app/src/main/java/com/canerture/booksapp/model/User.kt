package com.canerture.booksapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(@SerializedName("okay") @Expose var okay: Int,
                @SerializedName("e_mail") @Expose var e_mail: String,
                @SerializedName("password") @Expose var password: String,
                @SerializedName("name_surname") @Expose var name_surname: String,
                @SerializedName("phone_number") @Expose var phone_number: String): Serializable