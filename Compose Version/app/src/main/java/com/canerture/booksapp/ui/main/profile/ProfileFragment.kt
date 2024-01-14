package com.canerture.booksapp.ui.main.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.canerture.booksapp.databinding.FragmentProfileBinding
import com.canerture.booksapp.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<ProfileFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserInfo()

        with(binding) {

            signOutButton.setOnClickListener {
                viewModel.signOut().also {
                    val intent = Intent(requireActivity(), LoginActivity::class.java)
                    startActivity(intent)
                }
            }
        }

        initObservers()
    }

    private fun initObservers() = with(binding) {
        viewModel.profileState.observe(viewLifecycleOwner) {
            emailText.text = it.userData?.email
            nicknameText.text = it.userData?.nickname
            phoneNumberText.text = it.userData?.phoneNumber
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}