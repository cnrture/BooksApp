package com.canerture.booksapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.canerture.booksapp.data.model.BookBasket

@Dao
interface BooksBasketDAOInterface {

    @Insert
    fun addBookBasket(bookBasket: BookBasket)

    @Query("SELECT * FROM booksbasketdatabase")
    fun getBooksBasket(): List<BookBasket>?

    @Query("SELECT name FROM booksbasketdatabase")
    fun getBooksNamesBasket(): List<String>?

    @Query("DELETE FROM booksbasketdatabase WHERE id = :idInput")
    fun deleteBookWithId(idInput: Int)

    @Query("DELETE FROM booksbasketdatabase")
    fun clearBasket()

}