package com.example.findbook.view.favorite

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findbook.MainApplication
import com.example.findbook.R
import com.example.findbook.utils.SpacesItemDecoration
import com.example.findbook.view.bookdetail.BookDetailActivity
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {
    private var mAdapter: FavoriteAdapter? = null
    private var favoriteAdapterListener = object : FavoriteAdapter.FavoriteAdapterListener{
        override fun deleteBook(id: String) {
            MainApplication.database?.bookDao()?.deleteBook(id)
            mAdapter?.submitList(MainApplication.database?.bookDao()?.getBooks())
        }

        override fun onClickItem(url: String?) {
            startActivity(BookDetailActivity.newIntent(this@FavoriteActivity, url))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        initializeView()
    }

    private fun initializeView() {
        mAdapter = FavoriteAdapter(favoriteAdapterListener)
        mAdapter?.submitList(MainApplication.database?.bookDao()?.getBooks())
        listFavorite.layoutManager = LinearLayoutManager(this)
        listFavorite.addItemDecoration(SpacesItemDecoration(16))
        listFavorite.adapter = mAdapter
    }

    companion object {
        fun newIntent(activity: Activity): Intent {
            return Intent(activity, FavoriteActivity::class.java)
        }
    }
}
