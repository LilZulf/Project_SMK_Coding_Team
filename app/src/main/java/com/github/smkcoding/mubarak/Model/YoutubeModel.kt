package com.github.smkcoding.mubarak.Model


data class YoutubeModel(val items: List<Items>)

class Items(val snippet: Snippet)

class Snippet(
    val channelTitle: String,
    val title: String,
    val description: String,
    val thumbnails: Thumbnails,
    val resourceId: ResourceId
)

class Thumbnails(val medium: MediumPic)

class MediumPic(val url: String)

class ResourceId(val videoId: String)
