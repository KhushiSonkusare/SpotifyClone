package com.example.spotify.api.id

data class YoutubeVideo(
    val audio: List<Audio>,
    val channel: Channel,
    val id: String,
    val searchTerm: String,
    val title: String
)