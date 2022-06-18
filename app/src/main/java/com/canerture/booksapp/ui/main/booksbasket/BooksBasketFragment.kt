package com.canerture.booksapp.ui.main.booksbasket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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

        initObservers()

        with(binding) {

            booksBasketRecycleView.setHasFixedSize(true)

            booksBasketAdapter.onRemoveBasketClick = {
                viewModel.deleteBookFromBasket(it)
            }
        }
    }

    private fun initObservers() {

        with(binding) {

            with(viewModel) {

                isLoading.observe(viewLifecycleOwner) {
                    if (!it) booksLoadingView.visibility = View.GONE
                }

                booksBasket.observe(viewLifecycleOwner) { list ->
                    booksBasketAdapter.updateList(list)
                    booksBasketRecyclerAdapter = booksBasketAdapter
                    emptyBasketText.visibility = View.GONE

                    if (list.isNotEmpty()) {
                        goToPayButton.setOnClickListener {
                            it.findNavController()
                                .navigate(R.id.action_booksBasketFragment_to_paymentFragment)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}