package com.canerture.booksapp.data.repos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.canerture.booksapp.data.model.BookModel
import com.canerture.booksapp.data.model.UserModel
import com.canerture.booksapp.data.retrofit.ApiUtils
import com.canerture.booksapp.data.response.CRUDResponse
import com.canerture.booksapp.data.response.UserResponse
import com.canerture.booksapp.data.retrofit.UsersDAOInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersReporsitory {

    private var _userData = MutableLiveData<UserModel?>()
    val userData: LiveData<UserModel?>
        get() = _userData

    private val userDIF: UsersDAOInterface = ApiUtils.getUserDAOInterface()

    fun getUserData(): MutableLiveData<UserModel?> {
        return _userData
    }

    fun singUp(e_mail: String, password: String, name_surname: String, phone_number: String) {
        userDIF.signUp(e_mail, password, name_surname, phone_number)
            .enqueue(object : Callback<CRUDResponse> {
                override fun onResponse(
                    call: Call<CRUDResponse>,
                    response: Response<CRUDResponse>
                ) {
                }

                override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {
                    println(t.localizedMessage?.toString())
                }

            })
    }

    fun signIn(e_mail: String, password: String) {
        userDIF.signIn(e_mail, password).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {

                val id = response.body()?.id
                val eMail = response.body()?.eMail
                val nameSurname = response.body()?.nameSurname
                val phoneNumber = response.body()?.phoneNumber

                id?.let {
                    val userModel = UserModel(it, eMail!!, nameSurname!!, phoneNumber!!)
                    _userData.value = userModel
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                println(t.localizedMessage?.toString())
            }

        })
    }

}