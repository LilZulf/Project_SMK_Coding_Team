package com.github.smkcoding.mubarak.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.smkcoding.mubarak.Model.YoutubeModel
import com.github.smkcoding.mubarak.R
import com.github.smkcoding.mubarak.adapter.YoutubeListAdapter
import com.github.smkcoding.mubarak.constants.AppConstants
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_kajian.*
import kotlinx.android.synthetic.main.components_basic_actionbar.*
import okhttp3.*
import java.io.IOException


class Kajian : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kajian)

        viewProps()

        HorizontalList()
        VerticalList()
        MasjidChannel()
    }


    private fun viewProps() {
        rv_horizontal_channel.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_vertical_channel.layoutManager = LinearLayoutManager(this)
        rv_vertical_channel.hasFixedSize()
        rv_masjid_channel.layoutManager = LinearLayoutManager(this)
        rv_masjid_channel.hasFixedSize()
        // Set action bar title
        action_bar_title.text = getString(R.string.kajian_screen_title)
    }

    private fun HorizontalList() {

        val youtubeUrl = AppConstants.NU_CHANNEL
        val request = Request.Builder().url(youtubeUrl).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                Log.e("data response", body!!)
                val gson = GsonBuilder().create()
                val youtubelist = gson.fromJson(body, YoutubeModel::class.java)
                runOnUiThread {
                    rv_horizontal_channel.adapter = YoutubeListAdapter(
                        applicationContext,
                        youtubelist,
                        "default"
                    ) { youtubeModel: YoutubeModel, position: Int ->
                        Log.e(
                            "ONCLICK",
                            youtubeModel.items.get(position).snippet.channelTitle.toString()
                        )

                        val youtubeId = youtubeModel.items.get(position).snippet.resourceId.videoId

                        val intent = Intent(applicationContext, YoutubePlay::class.java)

                        intent.putExtra("VIDEO_ID", youtubeId)
                        intent.putExtra("YOUTUBE_URL", youtubeUrl)

                        startActivity(intent)
                    }
                }

            }

        })

    }

    private fun VerticalList() {

        val youtubeUrl = AppConstants.KHALID_BASALAMAH
        val request = Request.Builder().url(youtubeUrl).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()

                val gson = GsonBuilder().create()
                val youtubelist = gson.fromJson(body, YoutubeModel::class.java)

                runOnUiThread {
                    rv_vertical_channel.adapter = YoutubeListAdapter(
                        applicationContext,
                        youtubelist,
                        "other"
                    ) { youtubeModel: YoutubeModel, position: Int ->
                        Log.e(
                            "ONCLICK",
                            youtubeModel.items.get(position).snippet.channelTitle.toString()
                        )

                        val youtubeId = youtubeModel.items.get(position).snippet.resourceId.videoId

                        val intent = Intent(applicationContext, YoutubePlay::class.java)

                        intent.putExtra("VIDEO_ID", youtubeId)
                        intent.putExtra("YOUTUBE_URL", youtubeUrl)

                        startActivity(intent)
                    }
                }

            }

        })

    }

    private fun MasjidChannel() {

        val youtubeUrl = AppConstants.AL_GHIFARI
        val request = Request.Builder().url(youtubeUrl).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()

                val gson = GsonBuilder().create()
                val youtubelist = gson.fromJson(body, YoutubeModel::class.java)

                runOnUiThread {
                    rv_masjid_channel.adapter = YoutubeListAdapter(
                        applicationContext,
                        youtubelist,
                        "other"
                    ) { youtubeModel: YoutubeModel, position: Int ->
                        Log.e(
                            "ONCLICK",
                            youtubeModel.items.get(position).snippet.channelTitle.toString()
                        )

                        val youtubeId = youtubeModel.items.get(position).snippet.resourceId.videoId

                        val intent = Intent(applicationContext, YoutubePlay::class.java)

                        intent.putExtra("VIDEO_ID", youtubeId)
                        intent.putExtra("YOUTUBE_URL", youtubeUrl)

                        startActivity(intent)
                    }
                }

            }

        })

    }

}