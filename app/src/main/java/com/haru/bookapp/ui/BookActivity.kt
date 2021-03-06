package com.haru.bookapp.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.haru.bookapp.R
import com.haru.bookapp.model.Book
import com.haru.bookapp.ui.adapter.BookAdapter
import com.haru.bookapp.ui.adapter.BookView
import kotlinx.android.synthetic.main.activity_book.*


class BookActivity : AppCompatActivity(), BookView {

    private lateinit var adapter: BookAdapter
    private var sampleBookData = arrayListOf<Book>()
    val presenter:BookPresenter = BookPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        addSampleData()
        adapter = BookAdapter()
        adapter.setBookList(sampleBookData)
        adapter.setListener(object : BookAdapter.Listener{
            override fun onBookClick(book: Book,position:Int) {
                Toast.makeText(applicationContext, "Add ${book.title} to cart", Toast.LENGTH_SHORT).show()
                adapter.removeItem(position)
            }
        })

        recyclerViewBook?.adapter = adapter
        recyclerViewBook?.layoutManager = LinearLayoutManager(this)

        presenter.searchBook("")

    }

    override fun setAdapterData(items: ArrayList<Book>) {
        adapter.setBookList(items)
    }

    override fun showLoading(){
        recyclerViewBook?.visibility = View.GONE
        progressBar?.visibility = View.VISIBLE
    }
    override fun showContent(){
        recyclerViewBook?.visibility = View.VISIBLE
        progressBar?.visibility = View.GONE
    }

    // use in case of searching and not found or error handle.
    fun showUnavailable(message: String?) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    private fun updateBookList(booklist: ArrayList<Book>){
        booklist.let {
            adapter.setBookList(booklist)
            adapter.notifyDataSetChanged()
        }
    }

    private fun addSampleData(){
        for (i in 1..50){
            sampleBookData.add(Book("$i","Title $i","Subtitle $i", "Publisher $i", 200 + i))
        }
    }

}
