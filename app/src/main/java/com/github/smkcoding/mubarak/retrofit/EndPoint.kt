package com.github.smkcoding.mubarak.retrofit

import com.github.smkcoding.mubarak.model.SurahModel
import retrofit2.Call
import retrofit2.http.GET

interface EndPoint {
    @GET("quran/format/json/surat")
    fun getSurah(): Call<SurahModel>
}