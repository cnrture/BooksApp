package com.canerture.booksapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "booksbasketdatabase")
data class BookBasket(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "author")
    val author: String?,

    @ColumnInfo(name = "publisher")
    val publisher: String?,

    @ColumnInfo(name = "price")
    val price: String?,

    @ColumnInfo(name = "imageUrl")
    val imageUrl: String?,
)