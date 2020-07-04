package com.github.smkcoding.mubarak.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.smkcoding.mubarak.R
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.annotation.Nullable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.smkcoding.mubarak.session.SessionData
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.smkcoding.mubarak.adapter.KisahNabiAdapter
import com.github.smkcoding.mubarak.api.KisahNabiService
import com.github.smkcoding.mubarak.api.apiRequest
import com.github.smkcoding.mubarak.api.httpClient
import com.github.smkcoding.mubarak.item.KisahNabiItem
import androidx.fragment.app.Fragment
import com.github.smkcoding.mubarak.Util.dismissLoading
import com.github.smkcoding.mubarak.Util.showLoading
import com.github.smkcoding.mubarak.Util.toast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_kisah.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KisahActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kisah)
        SessionData.Session(this)
        callKisah()
    }
    private fun callKisah() {
        showLoading(this!!, swipeRefreshLayout)
        val httpClient = httpClient()
        val apiRequest = apiRequest<KisahNabiService>(httpClient, "http://smkcoding.tk/")
        val call = apiRequest.getKisahNabi()
        call.enqueue(object : Callback<List<KisahNabiItem>> {
            override fun onFailure(call: Call<List<KisahNabiItem>>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
                println(t)
            }

            override fun onResponse(
                call: Call<List<KisahNabiItem>>, response:
                Response<List<KisahNabiItem>>
            ) {
                dismissLoading(swipeRefreshLayout)
                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 ->
                                tampilKisahNabi(response.body()!!)
                            else -> {
                            }
                        }
                    else -> {
                    }
                }
            }
        })
    }
    private fun tampilKisahNabi(covCou: List<KisahNabiItem>) {
        listKisah.layoutManager = LinearLayoutManager(this)
        listKisah.adapter = KisahNabiAdapter(this!!, covCou) {
            val kisahNabi = it
            SessionData.Session(this)
            SessionData["id"] = kisahNabi.id.toString()
            val intent = Intent(this, DetailKisahNabiActivity::class.java)
            startActivity(intent)
            toast(this!!, kisahNabi.nama)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}
