package com.canerture.booksapp.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.booksapp.data.model.UserModel
import com.canerture.booksapp.data.repos.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileFragmentViewModel @Inject constructor(
    private val usersRepo: UsersRepository,
) : ViewModel() {

    private var _profileState = MutableLiveData<ProfileState>()
    val profileState: LiveData<ProfileState>
        get() = _profileState

    fun getUserInfo() = viewModelScope.launch {
        usersRepo.getUserInfo().fold(
            onSuccess = { userData ->
                _profileState.value = ProfileState(
                    isLoading = false,
                    userData = userData,
                )
            },
            onFailure = {
                _profileState.value = ProfileState(isLoading = false, errorMessage = it.message)
            }
        )
    }

    fun signOut() = usersRepo.signOut()
}

data class ProfileState(
    val isLoading: Boolean = false,
    val userData: UserModel? = null,
    val errorMessage: String? = null,
)