package com.ismin.android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

const val CREATED_BOOK_EXTRA_KEY = "CREATED_BOOK_EXTRA_KEY"

class CreateBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_book)
    }

    fun saveBook(view: View) {
        val title = findViewById<EditText>(R.id.a_create_book_edt_title).text.toString();
        val author = findViewById<EditText>(R.id.a_create_book_edt_author).text.toString();
        val date = findViewById<EditText>(R.id.a_create_book_edt_date).text.toString();

        val book = Book(title, author, date)
        val intent = Intent()
        intent.putExtra(CREATED_BOOK_EXTRA_KEY, book)
        setResult(RESULT_OK, intent)
        finish()
    }
}