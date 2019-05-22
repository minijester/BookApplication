package com.haru.bookapp.ui

import com.haru.bookapp.api.BookService
import com.haru.bookapp.model.BookResponse
import com.haru.bookapp.ui.adapter.BookView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class BookPresenter(private val view:BookView) {

    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://www.googleapis.com/books/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    fun searchBook(q : String?){
        view.showLoading()
        retrofit.create<BookService>(BookService::class.java)
            .searchBook(q)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: Observer<BookResponse> {
                override fun onComplete() {
                    view.showContent()
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(bookResponse: BookResponse) {
                    view.setAdapterData(bookResponse.books)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    view.showContent()
                }
            })
    }
}
