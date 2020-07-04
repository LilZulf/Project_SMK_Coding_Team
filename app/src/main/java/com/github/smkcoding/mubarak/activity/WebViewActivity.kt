package com.github.smkcoding.mubarak.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import com.github.smkcoding.mubarak.R
import com.github.smkcoding.mubarak.Util.toast
import com.github.smkcoding.mubarak.model.TbSurahModel
import com.github.smkcoding.mubarak.viewmodel.BookmarkViewModel
import kotlinx.android.synthetic.main.activity_web_view.*
import kotlinx.android.synthetic.main.components_basic_actionbar.*

class WebViewActivity : AppCompatActivity() {

    private var url : String? = ""
    private val viewModel by viewModels<BookmarkViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        viewModel.init(this)
        val intentData = intent.extras
        url = intentData!!.getString("link")
        val type = intentData.getString("type")
        if(type == "SURAH"){
            action_bar_title.text = intentData.getString("title")
            action_gear.visibility = View.VISIBLE
            action_gear.setImageResource(R.drawable.ic_bookmark)
            openWeb()
            action_gear.setOnClickListener {
                addBookmark()
            }
        }
        if(type=="BOOKMARK"){
            action_bar_title.text = intentData.getString("title")
            openWeb()
            action_gear.visibility = View.VISIBLE
            action_gear.setImageResource(R.drawable.ic_bookmark_black)
            openWeb()
            action_gear.setOnClickListener {
                deleteBookmark()
            }
        }else{
            openWeb()
        }


    }
    private fun openWeb(){
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

        //val url = intentData!!.getString("link")
        myWebView.loadUrl(url)
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
    private fun addBookmark(){
        val action = arrayOf("Iya", "Tidak")
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Apakah anda ingin menambah bookmark?")
        alert.setItems(action){ dialog, i ->
            when(i){
                0 -> {
                    val intentData = intent.extras
                    val nama = intentData!!.getString("title")
                    val ayat = intentData!!.getString("ayat")
                    val tipe = intentData!!.getString("tipeSurah")
                    val asma = intentData!!.getString("asma")
                    val nomor = intentData!!.getString("nomor")
                    val data = TbSurahModel(nama!!,ayat!!,tipe!!,asma!!,nomor!!)
                    action_gear.setImageResource(R.drawable.ic_bookmark_black)
                    action_gear.setOnClickListener {
//                        toast(this@WebViewActivity, "Berhasil Simpan Bookmark")
                        deleteBookmark()
                    }
                    viewModel.addData(data)
                }
                1 -> {

                }

            }

        }
        alert.create()
        alert.show()
        true
    }
    private fun deleteBookmark(){
        val action = arrayOf("Iya", "Tidak")
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Apakah anda ingin menghapus bookmark?")
        alert.setItems(action){ dialog, i ->
            when(i){
                0 -> {
                    val intentData = intent.extras
                    val nama = intentData!!.getString("title")
                    val ayat = intentData!!.getString("ayat")
                    val tipe = intentData!!.getString("tipeSurah")
                    val asma = intentData!!.getString("asma")
                    val nomor = intentData!!.getString("nomor")
                    val data = TbSurahModel(nama!!,ayat!!,tipe!!,asma!!,nomor!!)
                    toast(this@WebViewActivity, "Berhasil Hapus Bookmark")
                    action_gear.setImageResource(R.drawable.ic_bookmark)
                    viewModel.delete(data)
                    action_gear.setOnClickListener {
//                        toast(this@WebViewActivity, "Berhasil Simpan Bookmark")
                        addBookmark()
                    }
                }
                1 -> {

                }

            }

        }
        alert.create()
        alert.show()
        true
    }
}
