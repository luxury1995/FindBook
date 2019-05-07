package com.example.findbook.view.listbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findbook.MainApplication
import com.example.findbook.R
import com.example.findbook.model.BookResponse
import com.example.findbook.model.ItemResponse
import com.example.findbook.remote.ApiService
import com.example.findbook.remote.BookService
import com.example.findbook.utils.SpacesItemDecoration
import com.example.findbook.view.BookUIModel
import com.example.findbook.view.bookdetail.BookDetailActivity
import com.example.findbook.view.favorite.FavoriteActivity
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit
import kotlin.Comparator
import kotlin.collections.ArrayList

class BookActivity : AppCompatActivity() {
    private val disposable = CompositeDisposable()
    private var apiClient: BookService? = null
    private var mAdapter: BookAdapter? = null
    private var mListBookDetail: List<ItemResponse> = listOf()
    private var bookAdapterListener = object : BookAdapter.BookAdapterListener {
        override fun addFavoriteList(model: BookUIModel) {
            val listBookDetail: MutableList<BookUIModel> = mutableListOf()
            listBookDetail.add(model)
            MainApplication.database?.bookDao()?.insertBook(listBookDetail)
        }

        override fun onClickItem(url: String?) {
            startActivity(BookDetailActivity.newIntent(this@BookActivity, url))
        }
    }

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
                    }

                    override fun onResponse(call: Call<BookResponse>, response: Response<BookResponse>) {
                        mListBookDetail = response.body()?.items ?: listOf()
                        if (mListBookDetail.isEmpty()) {
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
        favorite.setOnClickListener {
            startActivity(FavoriteActivity.newIntent(this))
        }

        sort.setOnClickListener {
            if (mListBookDetail.isNotEmpty()) {
                mAdapter?.submitList(sortList(mListBookDetail as ArrayList<ItemResponse>))
            }
        }
    }

    private fun initializeView() {
        mAdapter = BookAdapter(bookAdapterListener)
        listBook.layoutManager = LinearLayoutManager(this)
        listBook.addItemDecoration(SpacesItemDecoration(16))
        listBook.adapter = mAdapter
    }

    private fun sortList(list: ArrayList<ItemResponse>): ArrayList<ItemResponse> {
        list.sortWith(Comparator { teamMember1, teamMember2 -> teamMember1.volumeInfo.title.compareTo(teamMember2.volumeInfo.title) })
        return list
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
