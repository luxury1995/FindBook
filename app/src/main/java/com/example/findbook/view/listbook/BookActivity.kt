package com.example.findbook.view.listbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findbook.R
import com.example.findbook.model.BookResponse
import com.example.findbook.remote.ApiService
import com.example.findbook.remote.BookService
import com.example.findbook.utils.SpacesItemDecoration
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit

class BookActivity : AppCompatActivity() {
    private val disposable = CompositeDisposable()
    private var apiClient: BookService? = null
    private var mAdapter: BookAdapter? = null

    init {
        apiClient = ApiService.client.create(BookService::class.java)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeView()
        initializeListener()
        initializeSearch()
    }

    private fun initializeSearch() {
        disposable.addAll(
            search.textChanges().debounce(250, TimeUnit.MILLISECONDS).subscribe {
                apiClient?.getBook(it.toString())?.enqueue(object : Callback<BookResponse> {
                    override fun onFailure(call: Call<BookResponse>, t: Throwable) {
                        // handel error
                    }

                    override fun onResponse(call: Call<BookResponse>, response: Response<BookResponse>) {
                        if (response.body()?.items?.isEmpty() == true || response.body()?.items == null) {
                            noContent.visibility = View.VISIBLE
                        } else {
                            noContent.visibility = View.GONE
                        }
                        mAdapter?.submitList(response.body()?.items)
                    }

                })
            }
        )
    }

    private fun initializeListener() {

    }

    private fun initializeView() {
        mAdapter = BookAdapter()
        listBook.layoutManager = LinearLayoutManager(this)
        listBook.addItemDecoration(SpacesItemDecoration(16))
        listBook.adapter = mAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
