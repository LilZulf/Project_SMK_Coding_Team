package com.github.smkcoding.mubarak.model

data class ArticleModel(
    var judul : String,
    var link : String,
    var image : String,
    var penulis : String,
    var tanggal : String,
    var key : String
) {

    constructor() : this("","","","","","")

}