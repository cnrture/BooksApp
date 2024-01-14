package com.canerture.booksapp.di

import android.content.Context
import androidx.room.Room
import com.canerture.booksapp.data.room.BooksBasketDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomDBModule {

    @Provides
    @Singleton
    fun provideRoomDB(
        @ApplicationContext context: Context
    ): BooksBasketDB = Room.databaseBuilder(
        context,
        BooksBasketDB::class.java,
        "booksbasketdatabase.db"
    ).allowMainThreadQueries().build()

    @Provides
    @Singleton
    fun provideBooksBasketDAO(
        booksBasketDB: BooksBasketDB
    ) = booksBasketDB.booksBasketDAOInterface()
}