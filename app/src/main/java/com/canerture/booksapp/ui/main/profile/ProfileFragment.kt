package com.canerture.booksapp.ui.main.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.canerture.booksapp.R
import com.canerture.booksapp.databinding.FragmentProfileBinding
import com.canerture.booksapp.ui.login.LoginActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { ProfileFragmentViewModel() }

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

        with(binding) {

            signOutButton.setOnClickListener {
                viewModel.signOut().also {
                    val intent = Intent(requireActivity(), LoginActivity::class.java)
                    startActivity(intent)
                }
            }

            viewModel.userInfo.observe(viewLifecycleOwner) {
                emailText.text = it.email
                nicknameText.text = it.nickname
                phoneNumberText.text = it.phoneNumber
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}