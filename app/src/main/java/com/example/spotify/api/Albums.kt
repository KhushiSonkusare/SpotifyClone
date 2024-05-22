package com.example.spotify.api

data class Albums(
    val items: List<Item>,
    val totalCount: Int
)