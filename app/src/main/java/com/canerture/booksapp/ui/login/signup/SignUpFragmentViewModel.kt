package com.canerture.booksapp.ui.login.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.booksapp.data.repos.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpFragmentViewModel @Inject constructor(
    private val usersRepo: UsersRepository,
) : ViewModel() {

    private var _signUpState = MutableLiveData<SignUpState>()
    val signUpState: LiveData<SignUpState>
        get() = _signUpState

    fun signUp(
        eMail: String,
        password: String,
        confirmPassword: String,
        nickname: String,
        phoneNumber: String,
    ) {
        viewModelScope.launch {
            if (checkFields(eMail, password, confirmPassword, nickname, phoneNumber)) {
                usersRepo.signUp(eMail, password, nickname, phoneNumber).fold(
                    onSuccess = {
                        _signUpState.value = SignUpState(
                            isLoading = false,
                            isSignUp = true
                        )
                    },
                    onFailure = {
                        _signUpState.value = SignUpState(isLoading = false, errorMessage = it.message)
                    },
                )
            }
        }
    }

    private fun checkFields(
        eMail: String,
        password: String,
        confirmPassword: String,
        nickname: String,
        phoneNumber: String,
    ): Boolean {
        return listOf(eMail, password, confirmPassword, nickname, phoneNumber).any(
            String::isNotBlank
        )
    }
}

data class SignUpState(
    val isLoading: Boolean = false,
    val isSignUp: Boolean = false,
    val errorMessage: String? = null,
)