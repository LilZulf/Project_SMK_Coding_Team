package com.github.smkcoding.mubarak.item

import com.google.gson.annotations.SerializedName

data class DoaItem(
    @SerializedName("arab")
    val arab: String,
    @SerializedName("arti")
    val arti: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("latin")
    val latin: String,
    @SerializedName("nama")
    val nama: String
)