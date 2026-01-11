package com.canerture.booksapp.ui.main.booksbasket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.canerture.booksapp.common.navigate
import com.canerture.booksapp.databinding.FragmentBooksBasketBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BooksBasketFragment : Fragment() {

    private var _binding: FragmentBooksBasketBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<BooksBasketFragmentViewModel>()

    private val booksBasketAdapter by lazy {
        BooksBasketAdapter(
            onRemoveBasketClick = { viewModel.deleteBookFromBasket(it) }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentBooksBasketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getBooksBasket()

        with(binding) {

            booksBasketRecycleView.setHasFixedSize(true)

            goToPayButton.setOnClickListener {
                navigate(BooksBasketFragmentDirections.booksToPayment())
            }
        }

        initObservers()
    }

    private fun initObservers() = with(binding) {

        viewModel.booksBasketState.observe(viewLifecycleOwner) { state ->

            booksLoadingView.isVisible = state.isLoading

            booksBasketRecycleView.isVisible = state.booksList != null
            emptyBasketText.isVisible = state.booksList == null
            goToPayButton.isEnabled = state.booksList != null

            state.booksList?.let {
                booksBasketAdapter.updateList(it)
                booksBasketRecycleView.adapter = booksBasketAdapter
            }

            state.errorMessage?.let {
                emptyBasketText.text = it
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}