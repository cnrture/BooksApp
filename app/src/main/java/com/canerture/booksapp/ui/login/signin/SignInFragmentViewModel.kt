package com.canerture.booksapp.ui.login.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.canerture.booksapp.data.repos.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInFragmentViewModel @Inject constructor(
    private val usersRepo: UsersRepository,
) : ViewModel() {

    private var _isSignIn = MutableLiveData<Boolean>()
    val isSignIn: LiveData<Boolean>
        get() = _isSignIn

    init {
        _isSignIn = usersRepo.isSignIn
    }

    fun signIn(eMail: String, password: String) = usersRepo.signIn(eMail, password)
}