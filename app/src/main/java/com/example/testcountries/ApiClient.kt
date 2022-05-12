package com.example.testcountries

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private val BASE_URL = "https://weepatient.com/"
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        HttpLoggingInterceptor.Level.BODY
    }
    private var retrofit: Retrofit? = null
    fun getClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpClient().newBuilder().addInterceptor(loggingInterceptor).build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}