package com.example.spotify.api

data class MusicData(
    val albums: Albums,
    val artists: Artists,
    val episodes: Episodes,
    val errorId: String,
    val genres: Genres,
    val playlists: Playlists,
    val podcasts: Podcasts,
    val status: Boolean,
    val tracks: Tracks,
    val users: Users
)