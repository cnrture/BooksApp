package com.canerture.booksapp.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserResponse(
    @SerializedName("id") @Expose var id: Int,
    @SerializedName("ic_e_mail") @Expose var eMail: String,
    @SerializedName("name_surname") @Expose var nameSurname: String,
    @SerializedName("phone_number") @Expose var phoneNumber: String,
    @SerializedName("success") @Expose var success: Int
)