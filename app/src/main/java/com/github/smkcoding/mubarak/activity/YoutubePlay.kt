package com.github.smkcoding.mubarak.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.smkcoding.mubarak.Model.YoutubeModel
import com.github.smkcoding.mubarak.R
import com.github.smkcoding.mubarak.Util.toast
import com.github.smkcoding.mubarak.adapter.YoutubeListAdapter
import com.github.smkcoding.mubarak.constants.AppConstants
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_youtube_player.*
import okhttp3.*
import java.io.IOException

class YoutubePlay : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private var youtubeId: String? = null
    private var youtubeUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_player)

        rv_video_playlist.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        //Get youtubeid and initialize
        getYoutubeId()
    }

    private fun getYoutubeId() {
        val intent = intent
        youtubeId = intent.getStringExtra("VIDEO_ID")
        youtubeUrl = intent.getStringExtra("YOUTUBE_URL")

        yt_pv.initialize(AppConstants.GOOGLE_DEVELOPER_KEY, this)
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        player: YouTubePlayer?,
        wasRestored: Boolean
    ) {
        if (!wasRestored) {
            player?.cueVideo(youtubeId)

            val request = Request.Builder().url(youtubeUrl!!).build()

            val client = OkHttpClient()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    println("Failed to execute request")
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body?.string()

                    val gson = GsonBuilder().create()
                    val youtubelist = gson.fromJson(body, YoutubeModel::class.java)

                    runOnUiThread {
                        rv_video_playlist.adapter = YoutubeListAdapter(
                            applicationContext,
                            youtubelist,
                            "article"
                        ) { youtubeModel: YoutubeModel, position: Int ->

                            val youtubeId =
                                youtubeModel.items.get(position).snippet.resourceId.videoId

                            player?.cueVideo(youtubeId)

                        }
                    }

                }

            })
        }
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        toast(this, "Youtube Initialization  Failure")
    }

}