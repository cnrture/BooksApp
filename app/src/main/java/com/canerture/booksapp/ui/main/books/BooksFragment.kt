package com.canerture.booksapp.ui.main.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.canerture.booksapp.common.hideKeyboard
import com.canerture.booksapp.common.setViewsGone
import com.canerture.booksapp.common.setViewsVisible
import com.canerture.booksapp.common.showSnackbar
import com.canerture.booksapp.databinding.FragmentBooksBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BooksFragment : Fragment() {

    private var _binding: FragmentBooksBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<BooksViewModel>()

    private val bestSellersAdapter by lazy {
        BestSellersAdapter(
            onBookClick = {
                val action = BooksFragmentDirections.actionBooksFragmentToBookDetailBottomSheet(it)
                findNavController().navigate(action)
            }
        )
    }

    private val allBooksAdapter by lazy {
        AllBooksAdapter(
            onAddBasketClick = {
                viewModel.addBookToBasket(it)
            },
            onBookClick = {
                val action = BooksFragmentDirections.actionBooksFragmentToBookDetailBottomSheet(it)
                findNavController().navigate(action)
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentBooksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getBooks()

        with(binding) {

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    allBooksAdapter.filter.filter(newText)

                    if (newText.isNullOrEmpty().not()) {
                        setViewsGone(bestSellersTitle, bestSellersRecycler)
                    } else {
                        setViewsVisible(bestSellersTitle, bestSellersRecycler)
                        hideKeyboard(requireActivity(), view)
                    }
                    return false
                }
            })
        }

        initObserver()
    }

    private fun initObserver() = with(binding) {

        viewModel.booksState.observe(viewLifecycleOwner) { state ->

            booksLoadingView.isVisible = state.isLoading

            state.booksList?.let { bookList ->
                allBooksAdapter.updateList(bookList)
                allBooksRecycler.setHasFixedSize(true)
                allBooksRecycler.adapter = allBooksAdapter
            }

            state.bestSellersList?.let { bestSellerList ->
                bestSellersAdapter.updateList(bestSellerList)
                bestSellersRecycler.setHasFixedSize(true)
            }

            state.errorMessage?.let {
                requireView().showSnackbar(it)
            }

            state.failMessage?.let {
                requireView().showSnackbar(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}