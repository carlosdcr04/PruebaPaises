package com.example.testcountries.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DsRespuesta: Serializable {
    @SerializedName("Paises")
    var paises: List<PaisesDTO>? = null
}