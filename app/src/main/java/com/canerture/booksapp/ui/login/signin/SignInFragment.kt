package com.canerture.booksapp.ui.login.signin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.canerture.booksapp.R
import com.canerture.booksapp.common.showSnackbar
import com.canerture.booksapp.databinding.FragmentSignInBinding
import com.canerture.booksapp.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<SignInFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            signInButton.setOnClickListener {
                val eMail = emailEditText.text.toString().trim()
                val password = passwordEditText.text.toString().trim()

                if (eMail.isNotEmpty() && password.isNotEmpty()) {
                    viewModel.signIn(eMail, password)
                }
            }
        }

        initObservers()
    }

    private fun initObservers() {

        viewModel.signInState.observe(viewLifecycleOwner) {
            if (it.isSignIn) {
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