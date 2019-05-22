package com.haru.bookapp.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_book_item.view.*

class BookViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun setBookImage(position:Int){
        if(position%2 == 0){
            Glide.with(itemView.context).load("https://www.packtpub.com/sites/default/files/B09544.png").into(itemView.imageBook)
        }
        else{
            Glide.with(itemView.context).load("https://pbpython.com/images/ml-cookbook.png").into(itemView.imageBook)
        }
    }

    fun setTitle(title: String) {
        itemView.textViewTitle?.text = title
    }

    fun setSubTitle(subTitle: String) {
        itemView.textViewSubTitle?.text = subTitle
    }

    fun setPublisher(publisher: String) {
        itemView.textViewAuthor?.text = publisher
    }

    fun setIsbn(isbn: String) {
        itemView.textViewIsbn?.text = isbn
    }

    fun setPageCount(pageCount: Int) {
        itemView.textViewPageCount?.text = String.format("%s pages", pageCount)
    }

    fun setOnBookClickListener(listener: View.OnClickListener) {
        itemView.setOnClickListener(listener)
    }
}