package com.sungje365.datasendtest.api

import com.google.gson.GsonBuilder
import com.sungje365.datasendtest.data.Music
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
        private var instance: RetrofitService? = null

        fun getInstance(): RetrofitService {
            if (instance == null) {
                val gson = GsonBuilder()
                    .setLenient()
                    .create()

                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

                instance = retrofit.create(RetrofitService::class.java)
            }

            return instance!!
        }
    }
}