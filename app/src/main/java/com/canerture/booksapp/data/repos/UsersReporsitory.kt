package com.canerture.booksapp.data.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.canerture.booksapp.data.model.UserModel
import com.canerture.booksapp.data.retrofit.ApiUtils
import com.canerture.booksapp.data.response.CRUDResponse
import com.canerture.booksapp.data.response.UserResponse
import com.canerture.booksapp.data.retrofit.UsersDAOInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersReporsitory {

    private var _userData = MutableLiveData<UserModel>()
    val userData: LiveData<UserModel>
        get() = _userData

    private var _isSignUp = MutableLiveData<Boolean>()
    val isSignUp: LiveData<Boolean>
        get() = _isSignUp

    private val userDIF: UsersDAOInterface = ApiUtils.getUserDAOInterface()

    fun getUserData(): MutableLiveData<UserModel> {
        return _userData
    }

    fun singUp(e_mail: String, password: String, name_surname: String, phone_number: String) {

        _isSignUp.value = false

        userDIF.signUp(e_mail, password, name_surname, phone_number)
            .enqueue(object : Callback<CRUDResponse> {
                override fun onResponse(
                    call: Call<CRUDResponse>,
                    response: Response<CRUDResponse>
                ) {
                    if (response.body()?.success == 1) {
                        _isSignUp.value = true
                    }
                }

                override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {
                    println(t.localizedMessage?.toString())
                }

            })
    }

    fun signIn(e_mail: String, password: String) {
        userDIF.signIn(e_mail, password).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {

                response.body()?.apply {
                    val id = id
                    val eMail = eMail
                    val nameSurname = nameSurname
                    val phoneNumber = phoneNumber

                    if (success == 1) {
                        val userModel = UserModel(id, eMail, nameSurname, phoneNumber)
                        _userData.value = userModel
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                println(t.localizedMessage?.toString())
            }

        })
    }

}