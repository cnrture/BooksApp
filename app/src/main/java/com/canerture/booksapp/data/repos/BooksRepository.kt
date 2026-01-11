package com.canerture.booksapp.data.repos

import com.canerture.booksapp.data.model.Book
import com.canerture.booksapp.data.model.BookBasket
import com.canerture.booksapp.data.retrofit.BooksService
import com.canerture.booksapp.data.room.BooksBasketDAO

class BooksRepository(
    private val booksService: BooksService,
    private val booksDAO: BooksBasketDAO,
) {
    suspend fun books(): Result<List<Book>> {
        return try {
            val response = booksService.allBooks()

            if (response.isSuccessful) {
                Result.success(response.body()?.books.orEmpty())
            } else {
                Result.failure(Exception(response.message()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun booksBasket(): Result<List<BookBasket>> {
        return try {
            val response = booksDAO.getBooksBasket()

            if (response.isNullOrEmpty()) {
                Result.failure(Exception("Basket is empty"))
            } else {
                Result.success(response)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun addBookToBasket(book: Book) {
        val bookBasket = BookBasket(
            name = book.name,
            author = book.author,
            publisher = book.publisher,
            price = book.price,
            imageUrl = book.imageUrl
        )

        booksDAO.addBookBasket(bookBasket)
    }

    fun deleteBookFromBasket(bookId: Int) = booksDAO.deleteBookWithId(bookId)

    fun clearBasket() = booksDAO.clearBasket()

}