package com.github.smkcoding.mubarak.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient

import com.github.smkcoding.mubarak.R
import kotlinx.android.synthetic.main.fragment_book.*

/**
 * A simple [Fragment] subclass.
 */
class bookFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myWebView: WebView = webView
        myWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                url: String?
            ): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        myWebView.loadUrl("https://quran.kemenag.go.id/")
        myWebView.settings.javaScriptEnabled = true
        myWebView.settings.allowContentAccess = true
        myWebView.settings.domStorageEnabled = true
        myWebView.settings.useWideViewPort = true
        myWebView.settings.setAppCacheEnabled(true)

        fun onBackPressed() {
            if (myWebView.canGoBack()) {
                // If web view have back history, then go to the web view back history
                myWebView.goBack()
            }
        }
    }



}



