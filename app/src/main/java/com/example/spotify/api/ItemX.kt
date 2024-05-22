package com.example.spotify.api

data class ItemX(
    val id: String,
    val name: String,
    val shareUrl: String,
    val type: String,
    val verified: Boolean,
    val visuals: Visuals
)