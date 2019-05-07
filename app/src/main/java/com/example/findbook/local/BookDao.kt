package com.example.findbook.local

import androidx.room.*
import com.example.findbook.view.BookUIModel

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(listBook : List<BookUIModel>)

    @Query("SELECT * FROM BookDetail")
    fun getBooks() : List<BookUIModel>

    @Query("DELETE FROM BookDetail where id = :id")
    fun deleteBook(id: String)
}