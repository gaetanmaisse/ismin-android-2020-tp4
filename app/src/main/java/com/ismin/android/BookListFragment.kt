package com.ismin.android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val ARG_BOOKS = "ARG_BOOKS📚"

class BookListFragment : Fragment() {
    private lateinit var books: ArrayList<Book>

    private lateinit var rcvBooks: RecyclerView
    private lateinit var bookAdapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        books = arguments!!.getSerializable(ARG_BOOKS) as ArrayList<Book>
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_book_list, container, false)

        this.rcvBooks = rootView.findViewById(R.id.f_book_list_rcv_books)
        bookAdapter = BookAdapter(books)
        this.rcvBooks.adapter = bookAdapter
        val linearLayoutManager = LinearLayoutManager(context)
        this.rcvBooks.layoutManager = linearLayoutManager

        val dividerItemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)
        this.rcvBooks.addItemDecoration(dividerItemDecoration)

        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance(booksToDisplay: ArrayList<Book>): BookListFragment {
            val bundle = Bundle()
            bundle.putSerializable(ARG_BOOKS, booksToDisplay)

            val bookListFragment = BookListFragment()
            bookListFragment.arguments = bundle

            return bookListFragment;
        }
    }
}