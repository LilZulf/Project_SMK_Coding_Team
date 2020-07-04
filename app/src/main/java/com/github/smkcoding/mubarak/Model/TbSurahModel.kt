package com.github.smkcoding.mubarak.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_bookmark")
data class TbSurahModel(
    var nama: String,
    var ayat: String,
    var type : String,
    var asma : String,
    @PrimaryKey var nomor: String
) {

    constructor() : this("","","","","")

}