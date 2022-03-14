package com.lcabral.countryapi.model

import com.google.gson.annotations.SerializedName
import com.lcabral.countryapi.data.Currency
import com.lcabral.countryapi.data.Flags

data class Country(
    val id: Int?,
    @SerializedName("name")
    val name: String? = "",
    val flags: Flags,
    val flag: String? = "",
    val capital: String? = "",
    val population: Int,
    val region: String? = "",
    val currency: Currency,
    val area: Double,
)
