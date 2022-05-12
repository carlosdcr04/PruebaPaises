package com.example.testcountries.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PaisesDTO: Serializable{
    @SerializedName("idPais")
    var idPais: String? = null

    @SerializedName("Pais")
    var pais: String? = null

    var clickeado: Boolean = false
}