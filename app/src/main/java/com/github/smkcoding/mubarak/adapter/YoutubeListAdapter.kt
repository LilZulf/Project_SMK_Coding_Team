package com.github.smkcoding.mubarak.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.smkcoding.mubarak.Model.YoutubeModel
import com.github.smkcoding.mubarak.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_youtube_vertical.view.*
import kotlinx.android.synthetic.main.item_horizontal_channel.view.*

class YoutubeListAdapter(
    val context: Context,
    private var items: YoutubeModel,
    private var type: String,
    private val listener: (YoutubeModel, position: Int) -> Unit
) : RecyclerView.Adapter<YoutubeListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): YoutubeListAdapter.ViewHolder {
        if (type.equals("default")) {
            return ViewHolder(
                context,
                LayoutInflater.from(context)
                    .inflate(R.layout.item_horizontal_channel, parent, false)
            )
        } else {
            return ViewHolder(
                context,
                LayoutInflater.from(context).inflate(R.layout.item_youtube_vertical, parent, false)
            )
        }
    }

    override fun getItemCount(): Int {
        return items.items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItem(items, listener)
        var video = items.items.get(position).snippet.title

        if (video.length > 45) {
            video = video.substring(0, 45) + "..."
        }

        if (type.equals("default")) {
            holder.containerView.video_title.text = video
            Glide.with(context)
                .load(items.items.get(position).snippet.thumbnails.medium.url)
                .into(holder.containerView.video_pic)
        } else {
            holder.containerView.article_title.text = video
            Glide.with(context)
                .load(items.items.get(position).snippet.thumbnails.medium.url)
                .into(holder.containerView.article_image)
        }

    }

    class ViewHolder(val context: Context, override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: YoutubeModel, listener: (YoutubeModel, position: Int) -> Unit) {
            containerView.setOnClickListener { listener(item, position) }
        }
    }

}