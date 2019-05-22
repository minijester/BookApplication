package com.haru.bookapp.ui.adapter

import com.haru.bookapp.model.Book

interface BookView {
    fun setAdapterData(items: ArrayList<Book>)
    fun showLoading()
    fun showContent()
}