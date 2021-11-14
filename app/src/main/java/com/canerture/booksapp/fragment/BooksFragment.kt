package com.canerture.booksapp.fragment

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import com.canerture.booksapp.R
import com.canerture.booksapp.adapter.BooksListAdapter
import com.canerture.booksapp.adapter.SearchBookAdapter
import com.canerture.booksapp.databinding.FragmentBooksBinding
import com.canerture.booksapp.viewmodel.BooksFragmentViewModel

class BooksFragment : Fragment() {

    private var _binding: FragmentBooksBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { BooksFragmentViewModel() }

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

        val concatAdapter = ConcatAdapter(searchBookAdapter, booksListAdapter)

        binding.apply {

            booksRecycleView.setHasFixedSize(true)

            viewModel.books.observe(viewLifecycleOwner, {
                if (it.isNullOrEmpty().not()) {
                    booksLoadingView.visibility = View.GONE
                    booksListAdapter.updateList(it)
                    booksListRecyclerAdapter = concatAdapter
                }
            })

            searchBookAdapter.searchText = {
                booksListAdapter.filter.filter(it)
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}