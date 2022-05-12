package com.example.testcountries

import com.example.testcountries.model.Post
import com.example.testcountries.request.PaisesRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("API/api/Utilidades/Pais_GetPais")
    fun getCountries(@Body request: PaisesRequest): Call<Post>
}