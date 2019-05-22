package com.haru.bookapp.api

import com.haru.bookapp.model.Book
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {
    @GET("volumes")
    fun getBook() : Call<List<Book>>

}