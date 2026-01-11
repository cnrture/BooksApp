package com.canerture.booksapp.di

import com.canerture.booksapp.data.repos.BooksRepository
import com.canerture.booksapp.data.repos.UsersRepository
import com.canerture.booksapp.data.retrofit.BooksService
import com.canerture.booksapp.data.room.BooksBasketDAO
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideBooksRepository(
        booksService: BooksService,
        booksDAO: BooksBasketDAO
    ) = BooksRepository(booksService, booksDAO)

    @Provides
    @Singleton
    fun provideUsersRepository(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore
    ) = UsersRepository(auth, firestore)
}