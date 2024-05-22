package com.example.spotify.api.id

data class MyData(
    val errorId: String,
    val spotifyTrack: SpotifyTrack,
    val status: Boolean,
    val youtubeVideo: YoutubeVideo
)