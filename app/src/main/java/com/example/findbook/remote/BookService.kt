package com.example.findbook.remote

import com.example.findbook.model.BookResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {
    @GET("v1/volumes/")
    fun getBook(@Query("q") query : String) : Call<BookResponse>
}