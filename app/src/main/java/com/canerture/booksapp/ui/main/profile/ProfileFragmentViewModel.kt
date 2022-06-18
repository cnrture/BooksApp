package com.canerture.booksapp.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.canerture.booksapp.data.model.UserModel
import com.canerture.booksapp.data.repos.UsersRepository

class ProfileFragmentViewModel() : ViewModel() {

    private val usersRepo = UsersRepository()

    private var _userInfo = MutableLiveData<UserModel>()
    val userInfo: LiveData<UserModel>
        get() = _userInfo

    init {
        getUserInfo()
    }

    private fun getUserInfo() {
        usersRepo.getUserInfo()
        _userInfo = usersRepo.userInfo
    }

    fun signOut() {
        usersRepo.signOut()
    }

}