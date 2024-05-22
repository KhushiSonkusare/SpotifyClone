package com.example.spotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spotify.api.ApiInterface
import com.example.spotify.api.MusicData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var myRecyclerView: RecyclerView
    lateinit var dataAdapter: DataAdapter
    lateinit var searchEditText: EditText

    var isApiCallInProgress = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myRecyclerView = findViewById(R.id.recylerView)
        searchEditText = findViewById(R.id.etSearch)

        val searchButton = findViewById<Button>(R.id.btnSearch)

        searchButton.setOnClickListener {

            if (!isApiCallInProgress) {
                isApiCallInProgress = true

                val searchTerm = searchEditText.text.toString()

                val retrofit = Retrofit.Builder()
                    .baseUrl("https://spotify-scraper.p.rapidapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val apiInterface = retrofit.create(ApiInterface::class.java)

                val retrofitData = apiInterface.getData(searchTerm)

                retrofitData.enqueue(object : Callback<MusicData?> {
                    override fun onResponse(call: Call<MusicData?>, response: Response<MusicData?>) {
                        isApiCallInProgress = false

                        if (response.isSuccessful) {
                            val data = response.body()?.tracks

                            dataAdapter = DataAdapter(this@MainActivity, data)
                            myRecyclerView.adapter = dataAdapter
                            myRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

                            Log.d("TAG: onResponse", "onResponse: ${response.body()}")
                        } else {
                            Log.d("TAG: onResponse", "onResponse: Failed - ${response.errorBody()?.string()}")
                        }
                    }

                    override fun onFailure(call: Call<MusicData?>, t: Throwable) {

                        isApiCallInProgress = false

                        Log.d("TAG: onFailure", "onFailure: $t")
                    }
                })
            }
        }
    }
}
