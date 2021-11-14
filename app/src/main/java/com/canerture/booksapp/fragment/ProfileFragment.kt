package com.canerture.booksapp.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.canerture.booksapp.R
import com.canerture.booksapp.databinding.FragmentProfileBinding

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
            requireActivity().getSharedPreferences("com.canerture.booksapp", Context.MODE_PRIVATE)

        binding.emailText.text = preferences.getString("e_mail", "e_mail")
        binding.nameSurnameText.text = preferences.getString("name_surname", "name_surname")
        binding.phoneNumberText.text = preferences.getString("phone_number", "phone_number")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}