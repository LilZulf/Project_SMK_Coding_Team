package com.github.smkcoding.mubarak.api

import com.github.smkcoding.mubarak.item.KisahNabiItem
import retrofit2.Call
import retrofit2.http.GET

interface KisahNabiService {
    @GET( "tb_kisahnabi/" )
    fun getKisahNabi(): Call<List<KisahNabiItem>>
}