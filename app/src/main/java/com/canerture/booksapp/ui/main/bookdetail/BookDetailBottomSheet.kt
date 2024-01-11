package com.canerture.booksapp.ui.main.bookdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.canerture.booksapp.R
import com.canerture.booksapp.common.showSnackbar
import com.canerture.booksapp.databinding.BookDetailBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookDetailBottomSheet : BottomSheetDialogFragment() {

    private var _binding: BookDetailBottomSheetBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<BookDetailBottomSheetViewModel>()

    private val args by navArgs<BookDetailBottomSheetArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = BookDetailBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val book = args.bookModel

        with(binding) {

            bookName.text = book.name
            bookAuthor.text = book.author
            bookPrice.text = "${book.price} ₺"

            Picasso.get().load(book.imageUrl).into(bookImage)

            addCartButton.setOnClickListener { viewModel.addBookToBasket(book) }

            viewModel.isBookAddedBasket.observe(viewLifecycleOwner) {
                if (it) dialog?.window!!.decorView.showSnackbar(getString(R.string.add_basket_snack_text))
                else dialog?.window!!.decorView.showSnackbar(getString(R.string.add_book_basket_error))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}