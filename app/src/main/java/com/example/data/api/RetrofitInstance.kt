package com.example.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitInstance {
    companion object {
        private const val api = "https://rickandmortyapi.com/api/"

        private val retrofit: Retrofit by lazy {
            // được khởi tạo duy nhất 1 lần trong 1 vòng đời application

            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor).build()

            Retrofit.Builder()
                .baseUrl(api)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun apiInterface(): UserService {
            return retrofit.create(UserService::class.java)
        }
    }
}