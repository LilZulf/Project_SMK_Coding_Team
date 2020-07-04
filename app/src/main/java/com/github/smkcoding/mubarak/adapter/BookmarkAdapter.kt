package com.github.smkcoding.mubarak.adapter

import android.app.AlertDialog
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
import com.github.smkcoding.mubarak.model.TbSurahModel
import com.github.smkcoding.mubarak.viewmodel.BookmarkViewModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_surah.view.*

class BookmarkAdapter(private val context: Context, private var items:
List<TbSurahModel>,var listener: (TbSurahModel)-> Unit) :
    RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {
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
        fun bindItem(item:TbSurahModel, listener: (TbSurahModel) -> Unit) {

            itemView.tvName.text = item.nama
            itemView.tvNumber.text = item.nomor
            itemView.tvDetail.text = item.type + " | "+item.ayat+" ayat"
            itemView.tvAsma.text = item.asma
            itemView.parentCard.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("link", "https://quran.kemenag.go.id/sura/"+item.nomor)
                bundle.putString("title", item.nama)
                bundle.putString("type", "BOOKMARK")
                bundle.putString("tipeSurah",item.type)
                bundle.putString("ayat",item.ayat)
                bundle.putString("nomor", item.nomor)
                bundle.putString("asma",item.asma)
                val i = Intent(context, WebViewActivity::class.java)
                i.putExtras(bundle)
                context.startActivity(i)
            }
            //Toast.makeText(context,item.key,Toast.LENGTH_LONG).show()

        }
    }

}