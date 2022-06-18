package com.canerture.booksapp.ui.main.bookdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.canerture.booksapp.R
import com.canerture.booksapp.common.showSnackbar
import com.canerture.booksapp.data.model.BooksBasketRoomModel
import com.canerture.booksapp.databinding.BookDetailBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso

class BookDetailBottomSheet : BottomSheetDialogFragment() {

    private var _binding: BookDetailBottomSheetBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { BookDetailBottomSheetViewModel(requireContext()) }

    private val args: BookDetailBottomSheetArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.book_detail_bottom_sheet, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val book = args.bookModel

        binding.apply {

            bookModel = book

            Picasso.get().load(book.book_image_url).into(bookImage)

            addCartButton.setOnClickListener {

                viewModel.addBookToBasket(
                    BooksBasketRoomModel(
                        bookName = book.book_name,
                        bookAuthor = book.book_author,
                        bookPublisher = book.book_publisher,
                        bookPrice = book.book_price,
                        bookImageUrl = book.book_image_url
                    )
                )
            }

            viewModel.isBookAddedBasket.observe(viewLifecycleOwner) {
                if (it) showSnackbar(dialog?.window!!.decorView, R.string.add_basket_snack_text)
                else showSnackbar(dialog?.window!!.decorView, R.string.add_book_basket_error)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}