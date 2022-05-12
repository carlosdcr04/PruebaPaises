package com.example.testcountries.model

import com.example.testcountries.model.response.DsRespuesta
import com.example.testcountries.model.response.PaisesDTO
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Post: Serializable{
    @SerializedName("dsRespuesta")
    var dsRespuesta: DsRespuesta? = null

    fun getCountry(): List<PaisesDTO>? {
        return dsRespuesta?.paises
    }
}