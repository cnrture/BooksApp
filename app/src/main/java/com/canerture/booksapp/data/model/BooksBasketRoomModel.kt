package com.canerture.booksapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "booksbasketdatabase")
data class BooksBasketRoomModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var bookId: Int = 0,

    @ColumnInfo(name = "bookName")
    var bookName: String?,

    @ColumnInfo(name = "bookAuthor")
    var bookAuthor: String?,

    @ColumnInfo(name = "bookPublisher")
    var bookPublisher: String?,

    @ColumnInfo(name = "bookPrice")
    var bookPrice: String?,

    @ColumnInfo(name = "bookImageUrl")
    var bookImageUrl: String?

)