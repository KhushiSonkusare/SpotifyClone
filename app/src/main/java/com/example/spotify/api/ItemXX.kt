package com.example.spotify.api

data class ItemXX(
    val cover: List<Cover>,
    val date: String,
    val description: String,
    val durationMs: Int,
    val durationText: String,
    val id: String,
    val name: String,
    val podcast: Podcast,
    val shareUrl: String,
    val type: String
)