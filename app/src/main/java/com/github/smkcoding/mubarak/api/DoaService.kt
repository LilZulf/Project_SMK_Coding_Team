package com.github.smkcoding.mubarak.api

import com.github.smkcoding.mubarak.item.DoaItem
import retrofit2.Call
import retrofit2.http.GET

interface DoaService {
    @GET( "tb_doa/" )
    fun getDoa(): Call<List<DoaItem>>
}