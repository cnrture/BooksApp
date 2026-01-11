package com.canerture.booksapp.ui.login.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.booksapp.common.Resource
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
                when (val response = usersRepo.signUp(eMail, password, nickname, phoneNumber)) {
                    is Resource.Success -> {
                        _signUpState.value = SignUpState(
                            isLoading = false,
                            isSignUp = true
                        )
                    }

                    is Resource.Fail -> {
                        _signUpState.value = SignUpState(isLoading = false, failMessage = response.message)
                    }

                    is Resource.Error -> {
                        _signUpState.value = SignUpState(isLoading = false, errorMessage = response.throwable.message)
                    }
                }
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
            String::isNullOrEmpty
        )
    }
}

data class SignUpState(
    val isLoading: Boolean = false,
    val isSignUp: Boolean = false,
    val failMessage: String? = null,
    val errorMessage: String? = null
)