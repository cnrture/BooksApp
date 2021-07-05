package com.canerture.booksapp.viewmodel

import androidx.lifecycle.ViewModel
import com.canerture.booksapp.repos.UsersReporsitory

class SignUpFragmentViewModel: ViewModel() {

    private var mrepo = UsersReporsitory()

    fun signUp(email:String, password:String, nameSurname:String, phoneNumber:String){
        mrepo.singUp(email, password, nameSurname, phoneNumber)
    }

}