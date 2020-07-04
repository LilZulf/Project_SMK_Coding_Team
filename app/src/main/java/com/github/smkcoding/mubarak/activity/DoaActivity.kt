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
import com.github.smkcoding.mubarak.adapter.DoaAdapter
import com.github.smkcoding.mubarak.api.DoaService
import com.github.smkcoding.mubarak.api.apiRequest
import com.github.smkcoding.mubarak.api.httpClient
import com.github.smkcoding.mubarak.item.DoaItem
import androidx.fragment.app.Fragment
import com.github.smkcoding.mubarak.Util.toast
import com.github.smkcoding.mubarak.Util.dismissLoading
import com.github.smkcoding.mubarak.Util.showLoading
import com.github.smkcoding.mubarak.Util.toast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_doa.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DoaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doa)
        SessionData.Session(this)
        callDoa()
    }
    private fun callDoa() {
        val httpClient = httpClient()
        val apiRequest = apiRequest<DoaService>(httpClient, "http://smkcoding.tk/")
        val call = apiRequest.getDoa()
        call.enqueue(object : Callback<List<DoaItem>> {
            override fun onFailure(call: Call<List<DoaItem>>, t: Throwable) {
                println("GAGAL$t")
            }

            override fun onResponse(
                call: Call<List<DoaItem>>, response:
                Response<List<DoaItem>>
            ) {
                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 ->
                                tampilDoa(response.body()!!)
                            else -> {
                            }
                        }
                    else -> {
                    }
                }
            }
        })
    }
    private fun tampilDoa(covCou: List<DoaItem>) {
        listDoa.layoutManager = LinearLayoutManager(this)
        listDoa.adapter = DoaAdapter(this, covCou) {
            val kumpulanDoa = it
            SessionData.Session(this)
            SessionData["uid"] = kumpulanDoa.id.toString()
            SessionData["arabic"] = kumpulanDoa.arab
            val intent = Intent(this, DetailDoaActivity::class.java)
            startActivity(intent)
            toast(this, kumpulanDoa.nama)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}
