package com.ismin.android

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class CreateBookFragment : Fragment() {

    private lateinit var listener: BookCreator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_create_book, container, false)

        rootView.findViewById<View>(R.id.f_create_book_view_clicker).setOnClickListener {
            listener.closeBookCreation()
        }

        rootView.findViewById<Button>(R.id.f_create_book_btn_save).setOnClickListener {
            val title =
                rootView.findViewById<EditText>(R.id.f_create_book_edt_title).text.toString();
            val author =
                rootView.findViewById<EditText>(R.id.f_create_book_edt_author).text.toString();
            val date = rootView.findViewById<EditText>(R.id.f_create_book_edt_date).text.toString();
            val book = Book(title, author, date)
            listener.onBookCreated(book)
        }


        ObjectAnimator.ofFloat(
            rootView.findViewById(R.id.constraintLayout),
            "translationY",
            300f,
            0f
        )
            .apply {
                interpolator = BounceInterpolator()
                duration = 5000
                start()
            }

        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BookCreator) {
            listener = context
        } else {
            throw RuntimeException("$context must implement BookCreator")
        }
    }

}

interface BookCreator {
    fun onBookCreated(book: Book)
    fun closeBookCreation()
}