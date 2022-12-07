package com.canerture.booksapp.ui.login

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.canerture.booksapp.ui.login.signin.SignInFragment
import com.canerture.booksapp.ui.login.signup.SignUpFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> SignInFragment()
        1 -> SignUpFragment()
        else -> SignInFragment()
    }
}