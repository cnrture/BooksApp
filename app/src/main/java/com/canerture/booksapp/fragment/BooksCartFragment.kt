package com.canerture.booksapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.canerture.booksapp.R
import com.canerture.booksapp.adapter.BooksCartAdapter
import com.canerture.booksapp.databinding.FragmentCartBooksBinding
import com.canerture.booksapp.model.Books
import com.canerture.booksapp.viewmodel.BooksCartFragmentViewModel

class BooksCartFragment : Fragment() {

    private lateinit var binding: FragmentCartBooksBinding

    private lateinit var viewModel : BooksCartFragmentViewModel

    private lateinit var adapter : BooksCartAdapter

    var tempCartBooksList = arrayListOf<Books>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart_books, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val temp : BooksCartFragmentViewModel by viewModels()
        viewModel = temp

        binding.basketBooksRecycleView.setHasFixedSize(true)

        viewModel.cartBooks.observe(viewLifecycleOwner, {
            adapter = BooksCartAdapter(it, viewModel)
            binding.booksCartAdapter = adapter
        })
    }
}