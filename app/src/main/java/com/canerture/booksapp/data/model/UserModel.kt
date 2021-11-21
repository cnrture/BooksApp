package com.canerture.booksapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserModel(@SerializedName("id") @Expose var id: Int?,
                     @SerializedName("e_mail") @Expose var eMail: String?,
                     @SerializedName("name_surname") @Expose var nameSurname: String?,
                     @SerializedName("phone_number") @Expose var phoneNumber: String?): Serializable