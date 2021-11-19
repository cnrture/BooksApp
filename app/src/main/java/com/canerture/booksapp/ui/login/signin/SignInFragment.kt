package com.canerture.booksapp.ui.login.signin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.canerture.booksapp.R
import com.canerture.booksapp.ui.main.MainActivity
import com.canerture.booksapp.databinding.FragmentSignInBinding
import com.google.android.material.snackbar.Snackbar

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

        val preferences =
            requireActivity().getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE)
        val editor = preferences!!.edit()

        binding.signInFragmentObject = this

        viewModel.eMailValidation.observe(viewLifecycleOwner, {
            if (!it) Snackbar.make(view, R.string.e_mail_not_valid, 1000).show()
        })

        viewModel.userData.observe(viewLifecycleOwner, {
            if (it?.id != null) {
                editor.putString(E_MAIL, it.eMail)
                editor.putString(NAME_SURNAME, it.nameSurname)
                editor.putString(PHONE_NUMBER, it.phoneNumber)
                editor.apply()
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
            } else {
                Snackbar.make(view, getString(R.string.wrong_email_password), 1000).show()
            }

        })
    }

    fun signInButton(email: String, password: String) {
        viewModel.signIn(email, password)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val PACKAGE_NAME = "com.canerture.booksapp"
        const val E_MAIL = "e_mail"
        const val NAME_SURNAME = "name_surname"
        const val PHONE_NUMBER = "phone_number"
    }
}