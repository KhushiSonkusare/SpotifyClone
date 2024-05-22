package com.example.spotify.api

data class Podcast(
    val cover: List<Cover>,
    val id: String,
    val name: String,
    val publisherName: String,
    val shareUrl: String,
    val type: String
)