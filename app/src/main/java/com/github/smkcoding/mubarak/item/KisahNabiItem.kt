package com.github.smkcoding.mubarak.item

import com.google.gson.annotations.SerializedName

data class KisahNabiItem(
    @SerializedName("deskripsi")
    val deskripsi: String,
    @SerializedName("gambar")
    val gambar: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("tahun_kelahiran")
    val tahunKelahiran: Int,
    @SerializedName("tempat")
    val tempat: String,
    @SerializedName("usia")
    val usia: Int
)