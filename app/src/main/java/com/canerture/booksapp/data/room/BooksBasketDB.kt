package com.canerture.booksapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.canerture.booksapp.data.model.BookBasket

@Database(entities = [BookBasket::class], version = 1)
abstract class BooksBasketDB : RoomDatabase() {

    abstract fun booksBasketDAOInterface(): BooksBasketDAO
}