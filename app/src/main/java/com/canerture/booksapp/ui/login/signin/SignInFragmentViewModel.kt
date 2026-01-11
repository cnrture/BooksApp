package com.canerture.booksapp.ui.login.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.booksapp.data.repos.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInFragmentViewModel @Inject constructor(
    private val usersRepo: UsersRepository,
) : ViewModel() {

    private var _signInState = MutableLiveData<SignInState>()
    val signInState: LiveData<SignInState>
        get() = _signInState

    fun signIn(eMail: String, password: String) = viewModelScope.launch {
        usersRepo.signIn(eMail, password).fold(
            onSuccess = {
                _signInState.value = SignInState(
                    isLoading = false,
                    isSignIn = true
                )
            },
            onFailure = {
                _signInState.value = SignInState(isLoading = false, errorMessage = it.message)
            },
        )
    }
}

data class SignInState(
    val isLoading: Boolean = false,
    val isSignIn: Boolean = false,
    val errorMessage: String? = null,
)