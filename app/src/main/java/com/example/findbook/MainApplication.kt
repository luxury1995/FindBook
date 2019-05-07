package com.example.findbook

import android.app.Application
import androidx.room.Room
import com.example.findbook.local.BookDatabase


class MainApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(applicationContext, BookDatabase::class.java, "book_database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
    companion object {
        var database : BookDatabase?=null
    }
}