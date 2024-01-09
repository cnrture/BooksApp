package com.canerture.booksapp.ui.login.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.canerture.booksapp.R
import com.canerture.booksapp.common.showSnackbar
import com.canerture.booksapp.databinding.FragmentSignUpBinding
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
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signUpFragmentObject = this
        initObservers()
    }

    private fun initObservers() = with(binding) {
        with(viewModel) {

            isInfosValid.observe(viewLifecycleOwner) {
                if (it.not())
                    requireView().showSnackbar(getString(R.string.incomplete_information_entered))
            }

            isValidMail.observe(viewLifecycleOwner) {
                emailInputLayout.error = if (!it) getString(R.string.invalid_mail) else ""
            }

            isPasswordMatch.observe(viewLifecycleOwner) {
                passwordInputLayout.error =
                    if (!it) getString(R.string.password_match_error) else ""
                confirmPasswordInputLayout.error =
                    if (!it) getString(R.string.password_match_error) else ""
            }

            isSignUp.observe(viewLifecycleOwner) {
                if (it) {
                    requireView().showSnackbar(getString(R.string.sign_up_snack_text))
                    clearFields()
                } else {
                    emailInputLayout.error = getString(R.string.registered_mail)
                }
            }
        }
    }

    fun signUpButton(
        email: String,
        password: String,
        confirmPassword: String,
        nickname: String,
        phoneNumber: String,
    ) = viewModel.signUp(email, password, confirmPassword, nickname, phoneNumber)

    private fun clearFields() = with(binding) {
        emailEditText.setText("")
        emailInputLayout.error = ""
        passwordEditText.setText("")
        passwordInputLayout.error = ""
        confirmPasswordEditText.setText("")
        confirmPasswordInputLayout.error = ""
        nicknameEditText.setText("")
        nicknameInputLayout.error = ""
        phoneNumberEditText.setText("")
        phoneNumberInputLayout.error = ""
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}