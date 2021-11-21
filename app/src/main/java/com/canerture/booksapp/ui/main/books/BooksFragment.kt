package com.canerture.booksapp.ui.main.books

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import com.canerture.booksapp.R
import com.canerture.booksapp.databinding.FragmentBooksBinding
import com.google.android.material.snackbar.Snackbar

class BooksFragment : Fragment() {

    private var _binding: FragmentBooksBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { BooksFragmentViewModel(requireContext()) }

    private val bestSellersAdapter by lazy { BestSellersListAdapter() }
    private val booksListAdapter by lazy { BooksListAdapter() }
    private val searchBookAdapter by lazy { SearchBookAdapter() }

    private var concatAdapter = ConcatAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_books, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        concatAdapter = ConcatAdapter(searchBookAdapter, bestSellersAdapter, booksListAdapter)

        initObservers()

        with(binding) {

            booksRecycleView.setHasFixedSize(true)

            searchBookAdapter.searchText = {
                if (it.isNullOrEmpty().not()) {
                    booksListAdapter.filter.filter(it)
                    concatAdapter.removeAdapter(bestSellersAdapter)
                } else {
                    booksListAdapter.filter.filter(it)
                    concatAdapter.addAdapter(1, bestSellersAdapter)
                }
            }

            booksListAdapter.onAddBasketClick = {
                if (viewModel.addBookToBasket(it).not()) {
                    Snackbar.make(view, R.string.add_book_basket_error, 1000).show()
                } else {
                    Snackbar.make(view, R.string.add_basket_snack_text, 1000).show()
                }
            }

        }
    }

    private fun initObservers() {

        with(binding) {

            with(viewModel) {

                isLoading.observe(viewLifecycleOwner, {
                    if (!it) booksLoadingView.visibility = View.GONE
                })

                bestSellersList.observe(viewLifecycleOwner, {
                    if (it.isNullOrEmpty().not()) bestSellersAdapter.updateList(it)
                })

                booksList.observe(viewLifecycleOwner, {
                    if (it.isNullOrEmpty().not()) {
                        booksListAdapter.updateList(it)
                        booksListRecyclerAdapter = concatAdapter
                    }
                })

            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}