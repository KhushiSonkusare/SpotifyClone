package com.example.spotify.api

data class Item(
    val artists: List<Artist>,
    val cover: List<Cover>,
    val date: String,
    val id: String,
    val name: String,
    val shareUrl: String,
    val type: String
)