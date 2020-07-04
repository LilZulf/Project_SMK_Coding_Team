package com.github.smkcoding.mubarak.activity

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.smkcoding.mubarak.R
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_detail_doa.*
import org.json.JSONObject
import com.github.smkcoding.mubarak.session.SessionData

class DetailDoaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_doa)
        SessionData.Session(this)
        callApiGetDoa()
    }
    private fun callApiGetDoa() {
        val id = SessionData["uid"]
        val arabic = SessionData["arabic"]
        val face = Typeface.createFromAsset(assets, "arabic-font.otf")
        arab.text = arabic
        arab.typeface = face
        val url = "http://smkcoding.tk/tb_doa/$id/show/"
        val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener {
            val jsonObject = JSONObject(it)
            judul.text = jsonObject.getString(("nama"))
            latin.text = jsonObject.getString(("latin"))
            arti.text = jsonObject.getString(("arti"))
        },
            Response.ErrorListener {
                Toast.makeText(this, "Kesalahan", Toast.LENGTH_SHORT)
                judul.text = "-"
            })
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }
}
