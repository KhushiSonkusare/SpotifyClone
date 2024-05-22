package com.example.spotify.api

data class Album(
    val cover: List<Cover>,
    val id: String,
    val name: String,
    val shareUrl: String,
    val type: String
)