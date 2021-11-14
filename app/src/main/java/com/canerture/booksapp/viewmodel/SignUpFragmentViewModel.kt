package com.canerture.booksapp.viewmodel

import androidx.lifecycle.ViewModel
import com.canerture.booksapp.repos.UsersReporsitory

class SignUpFragmentViewModel: ViewModel() {

    private var usersRepo = UsersReporsitory()

    fun signUp(email:String, password:String, nameSurname:String, phoneNumber:String){
        usersRepo.singUp(email, password, nameSurname, phoneNumber)
    }

}