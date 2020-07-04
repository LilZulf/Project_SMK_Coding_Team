package com.github.smkcoding.mubarak.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.smkcoding.mubarak.R
import com.github.smkcoding.mubarak.session.SessionData
import com.github.smkcoding.mubarak.item.KisahNabiItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.kisah_item.*
import java.util.*

class KisahNabiAdapter(private val context : Context, private val items :
List<KisahNabiItem>, private val listener : (KisahNabiItem)-> Unit) :
    RecyclerView.Adapter<KisahNabiAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(context, LayoutInflater.from(context).inflate(
            R.layout.kisah_item,
            parent, false))
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }
    class ViewHolder(val context : Context, override val containerView : View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer{
        @SuppressLint("SetTextI18n")
        fun bindItem(item: KisahNabiItem, listener: (KisahNabiItem) -> Unit) {
            txtKisah.text = item.nama
            txtInfo.text = "Info Selengkapnya"
            containerView.setOnClickListener { listener(item) }
        }
    }
}