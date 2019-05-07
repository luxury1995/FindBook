package com.example.findbook.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.findbook.view.BookUIModel
@Database(entities = [BookUIModel::class], version = 3, exportSchema = false)

abstract class BookDatabase : RoomDatabase(){
    abstract fun bookDao(): BookDao
}
