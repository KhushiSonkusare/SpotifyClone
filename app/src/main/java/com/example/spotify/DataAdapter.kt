package com.example.spotify

import android.app.Activity
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spotify.api.Tracks
import com.example.spotify.api.id.ApiIdInterface
import com.example.spotify.api.id.MyData
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class DataAdapter(val context: Activity, val datalist: Tracks?) :
    RecyclerView.Adapter<DataAdapter.MyViewHolder>() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return minOf(7, datalist?.items?.size ?: 0)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentData = datalist?.items?.get(position)
        if (currentData != null) {
            holder.title.text = currentData.name
            holder.artist.text = currentData.artists[0].name

            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://spotify-scraper.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

            val apiIdInterface = retrofit.create(ApiIdInterface::class.java)
            val trackdata = apiIdInterface.getData(currentData.name)
            Log.d("API CALL", "API CALL: ${currentData.name}")

            trackdata.enqueue(object : Callback<MyData?> {
                override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                    if (response.isSuccessful) {
                        val youtubeVideo = response.body()?.youtubeVideo
                        val spotfiyTrack = response.body()?.spotifyTrack
                        if (youtubeVideo != null) {
                            val audioUrl = youtubeVideo.audio.firstOrNull()?.url
                            val imageUrl = spotfiyTrack?.album?.cover?.firstOrNull()?.url

                            if (audioUrl != null && imageUrl != null) {
                                Picasso.get().load(imageUrl).into(holder.image)

                                // Set onClickListener for play button
                                holder.play.setOnClickListener {
                                    if (mediaPlayer == null) {
                                        mediaPlayer = MediaPlayer().apply {
                                            setDataSource(audioUrl)
                                            prepare()
                                            start()
                                        }
                                    } else {
                                        mediaPlayer?.start()
                                    }
                                }

                                // Set onClickListener for pause button
                                holder.pause.setOnClickListener {
                                    mediaPlayer?.pause()
                                }
                            }
                        }
                    } else {
                        Log.d("TAG: onResponseeee", "onResponse: Failed - ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<MyData?>, t: Throwable) {
                    Log.d("TAG: onFailureeee", "onFailure: $t")
                }
            })
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.IvAvatar)
        val title: TextView = itemView.findViewById(R.id.TvTrack)
        val artist: TextView = itemView.findViewById(R.id.TvArtist)
        val play: ImageButton = itemView.findViewById(R.id.IvPlay)
        val pause: ImageButton = itemView.findViewById(R.id.IvPause)
    }

    fun releaseMediaPlayer() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        releaseMediaPlayer()
    }
}
