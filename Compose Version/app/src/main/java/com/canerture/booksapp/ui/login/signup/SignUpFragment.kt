package com.canerture.booksapp.ui.login.signup

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.canerture.booksapp.R
import com.canerture.booksapp.common.showSnackbar
import com.canerture.booksapp.databinding.FragmentSignUpBinding
import com.canerture.booksapp.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<SignUpFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            signUpButton.setOnClickListener {
                viewModel.signUp(
                    eMail = emailEditText.text.toString(),
                    password = passwordEditText.text.toString(),
                    confirmPassword = confirmPasswordEditText.text.toString(),
                    nickname = nicknameEditText.text.toString(),
                    phoneNumber = phoneNumberEditText.text.toString()
                )
            }
        }

        initObservers()
    }

    private fun initObservers() {
        viewModel.signUpState.observe(viewLifecycleOwner) {
            if (it.isSignUp) {
                Intent(context, MainActivity::class.java).apply {
                    startActivity(this)
                    requireActivity().finish()
                }
            }

            if (it.errorMessage != null) {
                requireView().showSnackbar(getString(R.string.wrong_email_password))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}