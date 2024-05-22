package com.example.spotify.api.id

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiIdInterface {

    @Headers(
        "X-RapidAPI-Key: df17737d35msh87c39dae126d7a2p16c900jsnc490c594e4ef",
        "X-RapidAPI-Host: spotify-scraper.p.rapidapi.com"
    )
    @GET("v1/track/download")
    fun getData(
        @Query("track") track: String
    ): Call<MyData>
}