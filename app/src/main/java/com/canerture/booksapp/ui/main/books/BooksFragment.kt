package com.canerture.booksapp.ui.main.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.canerture.booksapp.R
import com.canerture.booksapp.common.hideKeyboard
import com.canerture.booksapp.common.showSnackbar
import com.canerture.booksapp.databinding.FragmentBooksBinding

class BooksFragment : Fragment() {

    private var _binding: FragmentBooksBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { BooksFragmentViewModel(requireContext()) }

    private val bestSellersAdapter by lazy { BestSellersAdapter() }
    private val allBooksAdapter by lazy { AllBooksAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_books, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()

        with(binding) {

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    allBooksAdapter.filter.filter(newText)

                    if (newText.isNullOrEmpty().not()) {
                        bestSellersTitle.visibility = View.GONE
                        bestSellersRecycler.visibility = View.GONE
                    } else {
                        bestSellersTitle.visibility = View.VISIBLE
                        bestSellersRecycler.visibility = View.VISIBLE
                        hideKeyboard(requireActivity(), view)
                    }
                    return false
                }
            })
        }
    }

    private fun initObservers() {

        with(binding) {

            with(viewModel) {

                isLoading.observe(viewLifecycleOwner) {
                    if (!it) booksLoadingView.visibility = View.GONE
                }

                bestSellersList.observe(viewLifecycleOwner) { list ->
                    bestSellersRecycler.apply {
                        setHasFixedSize(true)
                        adapter = bestSellersAdapter.also {
                            it.updateList(list)
                        }
                    }
                }

                booksList.observe(viewLifecycleOwner) { list ->
                    allBooksRecycler.apply {
                        setHasFixedSize(true)
                        adapter = allBooksAdapter.also { adapter ->
                            adapter.updateList(list)
                            adapter.onAddBasketClick = {
                                viewModel.addBookToBasket(it)
                            }
                        }
                    }
                }

                isBookAddedBasket.observe(viewLifecycleOwner) {
                    if (it) showSnackbar(requireView(), R.string.add_basket_snack_text)
                    else showSnackbar(requireView(), R.string.add_book_basket_error)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}