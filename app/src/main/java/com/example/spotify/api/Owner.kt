package com.example.spotify.api

data class Owner(
    val avatar: List<Avatar>,
    val id: String,
    val name: String,
    val shareUrl: String,
    val type: String
)