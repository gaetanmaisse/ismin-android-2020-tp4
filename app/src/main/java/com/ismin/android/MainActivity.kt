package com.ismin.android

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), BookCreator {
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.bookshelf.addBook(theLordOfTheRings)
        this.bookshelf.addBook(theHobbit)
        this.bookshelf.addBook(aLaRechercheDuTempsPerdu)

        displayList()
    }

    private fun displayList() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val bookListFragment = BookListFragment.newInstance(this.bookshelf.getAllBooks())

        fragmentTransaction.replace(R.id.a_main_lyt_fragment_container, bookListFragment)
        fragmentTransaction.commit()
    }

    private fun displayCreation() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val createBookFragment = CreateBookFragment()

        fragmentTransaction.replace(R.id.a_main_lyt_fragment_container, createBookFragment)
        fragmentTransaction.commit()
    }

    fun goToCreation(view: View) {
        displayCreation()
    }

    override fun onBookCreated(book: Book) {
        bookshelf.addBook(book)
        displayList()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_action_clear -> {
                bookshelf.clear()
                displayList()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}