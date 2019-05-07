package com.example.findbook.view.bookdetail

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.findbook.R
import android.webkit.WebView
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.annotation.TargetApi
import android.widget.Toast
import android.webkit.WebViewClient

class BookDetailActivity : AppCompatActivity() {
    private var mWebView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        initializeWebView()
    }

    private fun initializeWebView() {
        mWebView  = WebView(this)

        mWebView?.webViewClient = object : WebViewClient() {
            override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
                Toast.makeText(this@BookDetailActivity, description, Toast.LENGTH_SHORT).show()
            }
        }

        mWebView?.loadUrl(intent.getStringExtra(URL))
        setContentView(mWebView)

    }

    companion object {
        private const val URL = "url"
        fun newIntent(activity: Activity, url : String?) : Intent {
            val intent = Intent(activity, BookDetailActivity::class.java)
            intent.putExtra(URL, url)
            return intent
        }
    }
}
