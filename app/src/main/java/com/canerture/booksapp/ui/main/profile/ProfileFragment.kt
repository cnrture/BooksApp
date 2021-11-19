package com.canerture.booksapp.ui.main.profile

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.canerture.booksapp.R
import com.canerture.booksapp.databinding.FragmentProfileBinding
import com.canerture.booksapp.ui.login.signin.SignInFragment

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profileFragmentObject = this

        val preferences =
            requireActivity().getSharedPreferences(SignInFragment.PACKAGE_NAME, Context.MODE_PRIVATE)

        binding.emailText.text = preferences.getString(SignInFragment.E_MAIL, SignInFragment.E_MAIL)
        binding.nameSurnameText.text = preferences.getString(SignInFragment.NAME_SURNAME, SignInFragment.NAME_SURNAME)
        binding.phoneNumberText.text = preferences.getString(SignInFragment.PHONE_NUMBER, SignInFragment.PHONE_NUMBER)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}