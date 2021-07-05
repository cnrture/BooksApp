package com.canerture.booksapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.canerture.booksapp.model.User
import com.canerture.booksapp.repos.UsersReporsitory

class SignInFragmentViewModel: ViewModel() {

    private var mrepo = UsersReporsitory()
    var user = MutableLiveData<List<User>>()

    init {
        user = mrepo.userData()
    }

    fun signIn(email:String, password:String){
        mrepo.signIn(email, password)
    }

}