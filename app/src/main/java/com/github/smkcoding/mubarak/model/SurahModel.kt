package com.github.smkcoding.mubarak.model

data class SurahModel(
	val query: Query? = null,
	val hasil: ArrayList<HasilItem>? = null,
	val status: String? = null
)

data class Query(
	val format: String? = null,
	val surat: String? = null
)

data class HasilItem(
	val keterangan: String? = null,
	val rukuk: String? = null,
	val nama: String? = null,
	val ayat: String? = null,
	val name: String? = null,
	val start: String? = null,
	val urut: String? = null,
	val asma: String? = null,
	val arti: String? = null,
	val type: String? = null,
	val nomor: String? = null
)

