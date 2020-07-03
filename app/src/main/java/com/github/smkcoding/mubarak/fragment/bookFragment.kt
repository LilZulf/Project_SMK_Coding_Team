package com.github.smkcoding.mubarak.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.recyclerview.widget.LinearLayoutManager

import com.github.smkcoding.mubarak.R
import com.github.smkcoding.mubarak.adapter.SurahAdapter
import com.github.smkcoding.mubarak.model.SurahModel
import com.github.smkcoding.mubarak.retrofit.RequestRetrofit
import kotlinx.android.synthetic.main.fragment_book.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        getSurah()
//        val myWebView: WebView = webView
//        myWebView.webViewClient = object : WebViewClient() {
//            override fun shouldOverrideUrlLoading(
//                view: WebView?,
//                url: String?
//            ): Boolean {
//                view?.loadUrl(url)
//                return true
//            }
//        }
//        myWebView.loadUrl("https://quran.kemenag.go.id/")
//        myWebView.settings.javaScriptEnabled = true
//        myWebView.settings.allowContentAccess = true
//        myWebView.settings.domStorageEnabled = true
//        myWebView.settings.useWideViewPort = true
//        myWebView.settings.setAppCacheEnabled(true)
//
//        fun onBackPressed() {
//            if (myWebView.canGoBack()) {
//                // If web view have back history, then go to the web view back history
//                myWebView.goBack()
//            }
//        }
    }
    private fun getSurah() {
        val Model = RequestRetrofit.get().getSurah()
        Model.enqueue(object : Callback<SurahModel> {
            override fun onFailure(call: Call<SurahModel>, t: Throwable) {
//                dismissLoading(swipeRefreshLayout)
//                tampilToast(activity!!, t.message!!)
            }

            override fun onResponse(call: Call<SurahModel>, response: Response<SurahModel>) {
                if (response.body()!!.status == "ok") {
                    rvSurah.layoutManager = LinearLayoutManager(context)
                    rvSurah.adapter = SurahAdapter(context!!, response.body()!!.hasil!!) {
                    }

                } else {
                    //tampilToast(activity!!, response.body()!!.message!!)
                }
            }

        })
    }



}



