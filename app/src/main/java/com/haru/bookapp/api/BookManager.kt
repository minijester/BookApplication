package com.haru.bookapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BookManager {
    fun init(): BookService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.googleapis.com/books/v1/")
            .build()
        return retrofit.create(BookService::class.java)
    }
}