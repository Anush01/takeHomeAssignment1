package com.anushmp.takeawaymovieassignment.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.anushmp.takeawaymovieassignment.R
import org.jetbrains.anko.find

class BookMovieActivity : AppCompatActivity() {

    lateinit var bookMovieWebView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_movie)

        bookMovieWebView = find(R.id.bookMovieWebView)

        setupWebView()

    }

    private fun setupWebView() {

        bookMovieWebView.webViewClient = WebViewClient()

        var title = intent.getStringExtra("title")

        bookMovieWebView.apply {

            settings.javaScriptEnabled = true
            loadUrl("https://www.movietickets.com/#search=$title")
        }

    }
}