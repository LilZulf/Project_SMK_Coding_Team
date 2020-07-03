package com.github.smkcoding.mubarak.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.github.smkcoding.mubarak.R
import com.github.smkcoding.mubarak.model.MasjidModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.card_masjid.*
import kotlinx.android.synthetic.main.card_masjid.view.*


class MasjidAdapter(private val context: Context, private var items:
List<MasjidModel>, var listener: (MasjidModel)-> Unit) :
    RecyclerView.Adapter<MasjidAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            context, LayoutInflater.from(context).inflate(
                R.layout.card_masjid,
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
        fun bindItem(item: MasjidModel, listener: (MasjidModel) -> Unit) {
           itemView.tvName.text = item.nama
            itemView.tvAddress.text = item.alamat
            itemView.rlItem.setOnClickListener {
                Toast.makeText(context,item.key,Toast.LENGTH_LONG).show()
            }
        }
    }
}