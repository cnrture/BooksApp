package com.canerture.booksapp.ui.login.signin

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.canerture.booksapp.data.model.BookModel
import com.canerture.booksapp.data.model.UserModel
import com.canerture.booksapp.data.repos.UsersReporsitory

class SignInFragmentViewModel : ViewModel() {

    private var usersRepo = UsersReporsitory()

    private var _userData = MutableLiveData<UserModel?>()
    val userData: LiveData<UserModel?>
        get() = _userData

    private var _eMailValidation = MutableLiveData<Boolean>()
    val eMailValidation: LiveData<Boolean>
        get() = _eMailValidation

    init {
        _userData = usersRepo.getUserData()
    }

    fun signIn(email: String, password: String) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            usersRepo.signIn(email, password)
            _eMailValidation.value = true
        }   else {
            _eMailValidation.value = false
        }
    }

}