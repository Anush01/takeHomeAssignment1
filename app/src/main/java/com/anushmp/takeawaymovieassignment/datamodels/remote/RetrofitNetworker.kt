package com.anushmp.takeawaymovieassignment.datamodels.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitNetworker {

    companion object{

        //base url = https://api.themoviedb.org/3/movie/
        val base_url = "https://api.themoviedb.org/3/movie/"

        fun getRetrofitInstance():Retrofit{

            return Retrofit.Builder().baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder().build())
                .build()

        }


    }

}