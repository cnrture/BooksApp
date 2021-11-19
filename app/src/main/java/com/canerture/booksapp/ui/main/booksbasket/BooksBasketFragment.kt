package com.canerture.booksapp.ui.main.booksbasket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.canerture.booksapp.R
import com.canerture.booksapp.databinding.FragmentBooksBasketBinding

class BooksBasketFragment : Fragment() {

    private var _binding: FragmentBooksBasketBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { BooksBasketFragmentViewModel(requireContext()) }

    private val booksBasketAdapter by lazy { BooksBasketAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_books_basket, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            booksBasketRecycleView.setHasFixedSize(true)

            viewModel.isLoading.observe(viewLifecycleOwner, {
                if (!it) booksLoadingView.visibility = View.GONE
            })

            viewModel.booksBasket.observe(viewLifecycleOwner, {
                if (it.isNullOrEmpty().not()) {
                    booksBasketAdapter.updateList(it)
                    booksBasketRecyclerAdapter = booksBasketAdapter
                    emptyBasketText.visibility = View.GONE
                }
            })

            booksBasketAdapter.onRemoveBasketClick = {
                viewModel.deleteBookFromBasket(it)
            }

            goToPayButton.setOnClickListener {
                val action =
                    BooksBasketFragmentDirections.actionBooksBasketFragmentToPaymentFragment(129.90f)
                it.findNavController().navigate(action)
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}