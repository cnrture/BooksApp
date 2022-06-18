package com.canerture.booksapp.ui.login.signin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.canerture.booksapp.R
import com.canerture.booksapp.common.showSnackbar
import com.canerture.booksapp.databinding.FragmentSignInBinding
import com.canerture.booksapp.ui.main.MainActivity

class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { SignInFragmentViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signInFragmentObject = this

        with(viewModel) {

            isSignIn.observe(viewLifecycleOwner) {
                if (it) {
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    showSnackbar(view, R.string.wrong_email_password)
                }
            }
        }
    }

    fun signInButton(email: String, password: String) {
        viewModel.signIn(email, password)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}