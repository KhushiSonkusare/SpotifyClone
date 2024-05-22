package com.example.spotify.api.id

data class Audio(
    val durationMs: Int,
    val durationText: String,
    val format: String,
    val lastModified: Long,
    val mimeType: String,
    val size: Int,
    val sizeText: String,
    val url: String
)