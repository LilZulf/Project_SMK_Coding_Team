package com.github.smkcoding.mubarak.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.smkcoding.mubarak.R
import com.github.smkcoding.mubarak.activity.WebViewActivity
import com.github.smkcoding.mubarak.model.ArticleModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_article.view.*
import kotlinx.android.synthetic.main.item_article.view.article_title

class ArticleAdapter(private val context: Context, private var items:
List<ArticleModel>, var listener: (ArticleModel)-> Unit) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            context, LayoutInflater.from(context).inflate(
                R.layout.item_article,
                parent, false
            )
        )

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }

    class ViewHolder(val context: Context, override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: ArticleModel, listener: (ArticleModel) -> Unit) {

            itemView.article_title.text = item.judul
            itemView.article_desc.text = item.penulis+" | "+item.tanggal
            Glide.with(context)
                .load(item.image)
                .into(itemView.article_image_)
            itemView.article_parent.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("link", item.link)
                bundle.putString("type", "ARTICLE")
                val i = Intent(context, WebViewActivity::class.java)
                i.putExtras(bundle)
                context.startActivity(i)
            }

        }
    }
}