package com.sungje365.recyclerviewtest.api

import com.google.gson.GsonBuilder
import com.sungje365.recyclerviewtest.data.model.Music
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    @GET("2020-flo/song.json")
    fun getMusic(): Call<Music>

    companion object {
        private const val BASE_URL = "https://grepp-programmers-challenges.s3.ap-northeast-2.amazonaws.com/"
        private var retrofitService: RetrofitService? = null

        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val gson = GsonBuilder()
                    .setLenient()
                    .create()

                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

                retrofitService = retrofit.create(RetrofitService::class.java)
            }

            return retrofitService!!
        }
    }
}