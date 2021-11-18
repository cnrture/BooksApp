package com.canerture.booksapp.ui.login.signin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
            requireActivity().getSharedPreferences("com.canerture.booksapp", Context.MODE_PRIVATE)
        val editor = preferences!!.edit()

        binding.signInFragmentObject = this

        viewModel.eMailValidation.observe(viewLifecycleOwner, {
            if (!it) Snackbar.make(view, R.string.e_mail_not_valid, 1000).show()
        })

        viewModel.userData.observe(viewLifecycleOwner, {
            if (it?.id != null) {
                println(1)
                editor.putString("ic_e_mail", it.eMail)
                editor.putString("name_surname", it.nameSurname)
                editor.putString("phone_number", it.phoneNumber)
                editor.apply()
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
            } else {
                println(2)
                Toast.makeText(context, "Wrong E-mail or Password, Try Again !", Toast.LENGTH_SHORT)
                    .show()
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
}