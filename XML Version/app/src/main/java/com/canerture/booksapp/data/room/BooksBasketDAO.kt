package com.canerture.booksapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.canerture.booksapp.data.model.BookBasket

@Dao
interface BooksBasketDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBookBasket(bookBasket: BookBasket)

    @Query("SELECT * FROM booksbasketdatabase")
    fun getBooksBasket(): List<BookBasket>?

    @Query("DELETE FROM booksbasketdatabase WHERE id = :idInput")
    fun deleteBookWithId(idInput: Int)

    @Query("DELETE FROM booksbasketdatabase")
    fun clearBasket()
}