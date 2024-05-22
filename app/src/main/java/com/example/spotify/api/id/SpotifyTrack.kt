package com.example.spotify.api.id

data class SpotifyTrack(
    val album: Album,
    val artists: List<Artist>,
    val durationMs: Int,
    val durationText: String,
    val id: String,
    val name: String,
    val shareUrl: String,
    val type: String
)