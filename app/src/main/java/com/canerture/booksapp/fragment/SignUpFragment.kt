package com.canerture.booksapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.canerture.booksapp.databinding.FragmentSignUpBinding
import com.canerture.booksapp.viewmodel.SignUpFragmentViewModel

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel : SignUpFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val temp : SignUpFragmentViewModel by viewModels()
        viewModel = temp

        binding.signUpFragmentObject = this

    }

    fun signUpButton(email:String, password:String, nameSurname:String, phoneNumber:String) {
        viewModel.signUp(email, password, nameSurname, phoneNumber)
    }
}