package com.ismin.android

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private val createBookActivityRequestCode = 1;
    private val TAG = MainActivity::class.simpleName

    private val bookshelf = Bookshelf()
    private val theLordOfTheRings = Book(
        title = "The Lord of the Rings",
        author = "J. R. R. Tolkien",
        date = "1954-02-15"
    )

    private val theHobbit = Book(
        title = "The Hobbit",
        author = "J. R. R. Tolkien",
        date = "1937-09-21"
    )
    private val aLaRechercheDuTempsPerdu = Book(
        title = "À la recherche du temps perdu",
        author = "Marcel Proust",
        date = "1927"
    );

    private lateinit var rcvBooks: RecyclerView
    private lateinit var bookAdapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.bookshelf.addBook(theLordOfTheRings)
        this.bookshelf.addBook(theHobbit)
        this.bookshelf.addBook(aLaRechercheDuTempsPerdu)

        this.rcvBooks = findViewById(R.id.a_main_rcv_books)
        bookAdapter = BookAdapter(bookshelf.getAllBooks())
        this.rcvBooks.adapter = bookAdapter
        val linearLayoutManager = LinearLayoutManager(this)
        this.rcvBooks.layoutManager = linearLayoutManager

        val dividerItemDecoration = DividerItemDecoration(this, linearLayoutManager.orientation)
        this.rcvBooks.addItemDecoration(dividerItemDecoration)
    }

    fun goToCreation(view: View) {
        val intent = Intent(this, CreateBookActivity::class.java)
        startActivityForResult(intent, this.createBookActivityRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == this.createBookActivityRequestCode) {
            val book = data?.extras?.get(CREATED_BOOK_EXTRA_KEY) as Book
            bookshelf.addBook(book)
            bookAdapter.refreshData(bookshelf.getAllBooks())
            bookAdapter.notifyDataSetChanged()
        }
    }
}