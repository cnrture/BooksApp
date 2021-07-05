package com.canerture.booksapp.repos

import androidx.lifecycle.MutableLiveData
import com.canerture.booksapp.model.User
import com.canerture.booksapp.retrofit.ApiUtils
import com.canerture.booksapp.response.CRUDResponse
import com.canerture.booksapp.response.UserResponse
import com.canerture.booksapp.retrofit.UsersDAOInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersReporsitory {

    private var userData = MutableLiveData<List<User>>()
    private val userDIF : UsersDAOInterface = ApiUtils.getUserDAOInterface()

    init {
        userData = MutableLiveData()
    }

    fun userData() : MutableLiveData<List<User>>{
        return userData
    }

    fun singUp(e_mail: String, password: String, name_surname: String, phone_number: String) {
        userDIF.signUp(e_mail, password, name_surname, phone_number).enqueue(object : Callback<CRUDResponse>{
            override fun onResponse(call: Call<CRUDResponse>, response: Response<CRUDResponse>) {}

            override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {
                println(t.localizedMessage?.toString())
            }

        })
    }

    fun signIn(e_mail: String, password: String) {
        userDIF.signIn(e_mail, password).enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                val user = response.body()!!.users
                userData.value = user
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                println(t.localizedMessage?.toString())
            }

        })
    }

}