package com.canerture.booksapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.canerture.booksapp.R
import com.canerture.booksapp.adapter.BooksBasketAdapter
import com.canerture.booksapp.databinding.FragmentBooksBasketBinding
import com.canerture.booksapp.viewmodel.BooksBasketFragmentViewModel

class BooksBasketFragment : Fragment() {

    private var _binding: FragmentBooksBasketBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { BooksBasketFragmentViewModel() }

    private val booksBasketAdapter by lazy { BooksBasketAdapter(viewModel) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_books_basket, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            booksBasketRecycleView.setHasFixedSize(true)

            viewModel.booksBasket.observe(viewLifecycleOwner, {
                if (it.isNullOrEmpty().not()) {
                    booksLoadingView.visibility = View.GONE
                    booksBasketAdapter.updateList(it)
                    booksBasketRecyclerAdapter = booksBasketAdapter
                }
            })

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}