package com.haru.bookapp.api

import com.haru.bookapp.model.Book
import com.haru.bookapp.model.BookResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {
    @GET("volumes")
    fun searchBook(@Query("q") q: String?): Observable<BookResponse>

}