package com.github.smkcoding.mubarak.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.smkcoding.mubarak.R
import android.view.LayoutInflater
import android.widget.Toast
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_detail_kisah_nabi.*
import org.json.JSONObject
import kotlin.collections.ArrayList
import com.github.smkcoding.mubarak.session.SessionData

class DetailKisahNabiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_kisah_nabi)
        SessionData.Session(this)
        callApiGetDetailKisah()
    }
    @SuppressLint("SetTextI18n")
    private fun callApiGetDetailKisah() {
        val id = SessionData["id"]
        val url = "http://smkcoding.tk/tb_kisahnabi/$id/show/"
        val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener {
            val jsonObject = JSONObject(it.toString())
            val des = jsonObject.getString(("deskripsi"))
            val us = jsonObject.getString(("tahun_kelahiran"))
            description.text = des.replace("\\n", "\n")
            usia.text = jsonObject.getString(("usia"))
            thn.text = "$us SM"
            tmp.text = jsonObject.getString(("tempat"))
        },
            Response.ErrorListener {
                Toast.makeText(this, "Kesalahan", Toast.LENGTH_SHORT)
                description.text = "-"
            })
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }
}
