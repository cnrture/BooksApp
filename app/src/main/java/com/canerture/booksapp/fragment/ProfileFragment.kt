package com.canerture.booksapp.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.canerture.booksapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profileFragmentObject = this

        val prefences = requireActivity().getSharedPreferences("com.canerture.booksapp", Context.MODE_PRIVATE)

        binding.emailText.text = prefences.getString("e_mail", "e_mail")
        binding.nameSurnameText.text = prefences.getString("name_surname", "name_surname")
        binding.phoneNumberText.text = prefences.getString("phone_number", "phone_number")
    }


}