package com.canerture.booksapp.response

import com.canerture.booksapp.model.User
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserResponse(
    @SerializedName("users") @Expose var users: List<User>,
    @SerializedName("success") @Expose var success: Int
)