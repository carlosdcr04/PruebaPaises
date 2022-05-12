package com.example.testcountries.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PaisesRequest(@SerializedName("cadena")
                         var cadena: String = ""): Serializable