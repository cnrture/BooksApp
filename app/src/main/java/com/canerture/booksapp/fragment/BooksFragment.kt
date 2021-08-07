package com.canerture.booksapp.fragment

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.canerture.booksapp.adapter.BooksAdapter
import com.canerture.booksapp.R
import com.canerture.booksapp.databinding.FragmentBooksBinding
import com.canerture.booksapp.model.Books
import com.canerture.booksapp.viewmodel.BooksFragmentViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso

class BooksFragment : Fragment(), SearchView.OnQueryTextListener, BooksAdapter.ClickedBookListener {

    private lateinit var binding: FragmentBooksBinding
    private lateinit var viewModel : BooksFragmentViewModel
    private lateinit var adapter : BooksAdapter
    private lateinit var materialAlertDialogBuilder: MaterialAlertDialogBuilder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_books, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val temp : BooksFragmentViewModel by viewModels()
        viewModel = temp

        setHasOptionsMenu(true)
        materialAlertDialogBuilder = MaterialAlertDialogBuilder(context!!)

        binding.booksFragmentObject = this

        binding.toolbar.title = ""
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        binding.booksRecycleView.setHasFixedSize(true)

        viewModel.books.observe(viewLifecycleOwner, {
            adapter = BooksAdapter(it, this)
            binding.booksAdapter = adapter
        })

        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->

            val chip: Chip? = group.findViewById(checkedId)
            chip?.let {
                if (it.isChecked) {
                    viewModel.getSearchedBooks(it.text.toString())
                }   else {
                    viewModel.getBooks()
                }

            } ?: kotlin.run {
                viewModel.getBooks()
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu,menu)

        val item = menu.findItem(R.id.search_book)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.add_book -> addBookButton()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        viewModel.getSearchedBooks(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        viewModel.getSearchedBooks(newText)
        return true
    }

    override fun onResume() {
        super.onResume()
        viewModel.getBooks()
    }

    private fun addBookButton() {

        val abaDesign = LayoutInflater.from(context).inflate(R.layout.add_book_alert, null, false)
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setView(abaDesign)

        val dialog = alertDialogBuilder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val bookNameInput: TextInputEditText = abaDesign.findViewById(R.id.bookNameInput)
        val bookAuthorInput: TextInputEditText = abaDesign.findViewById(R.id.bookAuthorInput)
        val bookPublisherInput: TextInputEditText = abaDesign.findViewById(R.id.bookPublisherInput)
        val bookPriceInput: TextInputEditText = abaDesign.findViewById(R.id.bookPriceInput)
        val bookImageUrlInput: TextInputEditText = abaDesign.findViewById(R.id.bookImageUrlInput)

        val addBookSubmitButton : Button = abaDesign.findViewById(R.id.addBookSubmitButton)
        addBookSubmitButton.setOnClickListener {
            viewModel.addBook(bookNameInput.text.toString(), bookAuthorInput.text.toString(), bookPublisherInput.text.toString(), bookPriceInput.text.toString(), bookImageUrlInput.text.toString())
            Snackbar.make(it, "Product has been successfully added !", Snackbar.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        val backButton : Button = abaDesign.findViewById(R.id.cancelButton)
        backButton.setOnClickListener {
            dialog.dismiss()
        }
    }

    override fun onClickedBookListener(data: Books, pos: Int) {

        val bdaDesign = LayoutInflater.from(context).inflate(R.layout.book_detail_alert, null, false)
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setView(bdaDesign)

        val bookName: TextView = bdaDesign.findViewById(R.id.bookName)
        val bookAuthor: TextView = bdaDesign.findViewById(R.id.bookAuthor)
        val bookPublisher: TextView = bdaDesign.findViewById(R.id.bookPublisher)
        val bookPrice: TextView = bdaDesign.findViewById(R.id.bookPrice)
        val bookImage: ImageView = bdaDesign.findViewById(R.id.bookImage)
        bookName.text = data.book_name
        bookAuthor.text = data.book_author
        bookPublisher.text = data.book_publisher
        bookPrice.text = data.book_price + " ₺"
        Picasso.get().load(data.book_image_url).into(bookImage)

        val dialog = alertDialogBuilder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val addCartButton : Button = bdaDesign.findViewById(R.id.addCartButton)
        addCartButton.setOnClickListener {
            viewModel.addCartBook(data.book_id)
            Snackbar.make(it, "Product has been successfully added to the cart !", Snackbar.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        val cancelButton : Button = bdaDesign.findViewById(R.id.cancelButton)
        cancelButton.setOnClickListener {
            dialog.dismiss()
        }
    }

}