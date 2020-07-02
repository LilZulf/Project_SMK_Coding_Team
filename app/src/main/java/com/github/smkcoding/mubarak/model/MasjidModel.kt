package com.github.smkcoding.mubarak.model

data class MasjidModel(
    var nama : String,
    var alamat : String,
    var longitude : String,
    var latitude : String,
    var image : String,
    var key : String
) {

    constructor() : this("","","","","","")

}