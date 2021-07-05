package com.canerture.booksapp.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.canerture.booksapp.activity.MainActivity
import com.canerture.booksapp.databinding.FragmentSignInBinding
import com.canerture.booksapp.viewmodel.SignInFragmentViewModel

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var viewModel : SignInFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val temp : SignInFragmentViewModel by viewModels()
        viewModel = temp

        val prefences = requireActivity().getSharedPreferences("com.canerture.booksapp", Context.MODE_PRIVATE)
        val editor = prefences!!.edit()

        binding.signInFragmentObject = this

        viewModel.user.observe(viewLifecycleOwner, {
            if (it[0].okay == 1) {
                editor.putString("e_mail", it[0].e_mail)
                editor.putString("name_surname", it[0].name_surname)
                editor.putString("phone_number", it[0].phone_number)
                editor.commit()
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
            }   else {
                Toast.makeText(context, "Wrong E-mail or Password, Try Again !", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun signInButton(email:String, password:String) {
        viewModel.signIn(email, password)
    }

}