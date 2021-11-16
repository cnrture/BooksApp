package com.canerture.booksapp.ui.main.books

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import com.canerture.booksapp.R
import com.canerture.booksapp.databinding.FragmentBooksBinding

class BooksFragment : Fragment() {

    private var _binding: FragmentBooksBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { BooksFragmentViewModel() }

    private val bestSellersAdapter by lazy { BestSellersListAdapter() }
    private val booksListAdapter by lazy { BooksListAdapter() }
    private val searchBookAdapter by lazy { SearchBookAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_books, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val concatAdapter = ConcatAdapter(searchBookAdapter, bestSellersAdapter, booksListAdapter)

        binding.apply {

            booksRecycleView.setHasFixedSize(true)

            viewModel.isLoading.observe(viewLifecycleOwner, {
                if (!it) booksLoadingView.visibility = View.GONE
            })

            viewModel.bestSellersList.observe(viewLifecycleOwner, {
                if (it.isNullOrEmpty().not()) bestSellersAdapter.updateList(it)
            })

            viewModel.booksList.observe(viewLifecycleOwner, {
                if (it.isNullOrEmpty().not()) {
                    booksListAdapter.updateList(it)
                    booksListRecyclerAdapter = concatAdapter
                }
            })

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
                viewModel.addCartBook(it)
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}