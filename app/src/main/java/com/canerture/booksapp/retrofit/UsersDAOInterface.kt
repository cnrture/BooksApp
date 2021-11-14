package com.canerture.booksapp.retrofit

import com.canerture.booksapp.response.CRUDResponse
import com.canerture.booksapp.response.UserResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UsersDAOInterface {

    @POST("/sign_up.php")
    @FormUrlEncoded
    fun signUp(
        @Field("e_mail") e_mail: String,
        @Field("password") password: String,
        @Field("name_surname") name_surname: String,
        @Field("phone_number") phone_number: String
    ): Call<CRUDResponse>

    @POST("/sign_in.php")
    @FormUrlEncoded
    fun signIn(
        @Field("e_mail") e_mail: String,
        @Field("password") password: String
    ): Call<UserResponse>

}