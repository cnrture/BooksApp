package com.canerture.booksapp.ui.login.signup

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.canerture.booksapp.data.repos.UsersReporsitory

class SignUpFragmentViewModel : ViewModel() {

    private var usersRepo = UsersReporsitory()

    private var _isInfosValid = MutableLiveData<Boolean>()
    val isInfosValid: LiveData<Boolean>
        get() = _isInfosValid

    private var _isMailValid = MutableLiveData<Boolean>()
    val isMailValid: LiveData<Boolean>
        get() = _isMailValid

    fun signUp(email: String, password: String, nameSurname: String, phoneNumber: String) {

        if (email.isNotEmpty() && password.isNotEmpty() && nameSurname.isNotEmpty() && phoneNumber.isNotEmpty()) {
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                usersRepo.singUp(email, password, nameSurname, phoneNumber)
                _isMailValid.value = true
                _isInfosValid.value = true
            } else {
                _isMailValid.value = false
            }
        }   else {
            _isInfosValid.value = false
        }

    }
}