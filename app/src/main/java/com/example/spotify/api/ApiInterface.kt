package com.example.spotify.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {

    @Headers(
        "X-RapidAPI-Key: df17737d35msh87c39dae126d7a2p16c900jsnc490c594e4ef",
        "X-RapidAPI-Host: spotify-scraper.p.rapidapi.com"
    )
    @GET("v1/search")
    fun getData(
        @Query("term") term: String
    ): Call<MusicData>
}