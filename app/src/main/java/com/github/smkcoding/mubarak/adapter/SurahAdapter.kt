package com.github.smkcoding.mubarak.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.recyclerview.widget.RecyclerView
import com.github.smkcoding.mubarak.R
import com.github.smkcoding.mubarak.activity.WebViewActivity
import com.github.smkcoding.mubarak.model.HasilItem
import com.github.smkcoding.mubarak.model.SurahModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_surah.view.*

class SurahAdapter(private val context: Context, private var items:
List<HasilItem>, var listener: (HasilItem)-> Unit) :
    RecyclerView.Adapter<SurahAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            context, LayoutInflater.from(context).inflate(
                R.layout.item_surah,
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
        fun bindItem(item:HasilItem, listener: (HasilItem) -> Unit) {

                itemView.tvName.text = item.nama
                itemView.tvNumber.text = item.nomor
                itemView.tvDetail.text = item.type + " | "+item.ayat+" ayat"
                 itemView.tvAsma.text = item.asma
                //Toast.makeText(context,item.key,Toast.LENGTH_LONG).show()
                itemView.parentCard.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("link", "https://quran.kemenag.go.id/sura/"+item.nomor)
                    val i = Intent(context, WebViewActivity::class.java)
                    i.putExtras(bundle)
                    context.startActivity(i)
                }
        }
    }
}