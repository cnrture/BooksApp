package com.canerture.booksapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.canerture.booksapp.R
import com.canerture.booksapp.databinding.FragmentSignUpBinding
import com.canerture.booksapp.viewmodel.SignUpFragmentViewModel

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { SignUpFragmentViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpFragmentObject = this

    }

    fun signUpButton(email: String, password: String, nameSurname: String, phoneNumber: String) {
        viewModel.signUp(email, password, nameSurname, phoneNumber)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}