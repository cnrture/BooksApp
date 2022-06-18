package com.canerture.booksapp.ui.login.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.canerture.booksapp.data.repos.UsersRepository

class SignInFragmentViewModel : ViewModel() {

    private var usersRepo = UsersRepository()

    private var _isSignIn = MutableLiveData<Boolean>()
    val isSignIn: LiveData<Boolean>
        get() = _isSignIn

    init {
        _isSignIn = usersRepo.isSignIn
    }

    fun signIn(eMail: String, password: String) {
        usersRepo.signIn(eMail, password)
    }
}