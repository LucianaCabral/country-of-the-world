package com.lcabral.countryapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country @JvmOverloads constructor(
    @SerializedName("name")
    val name: String? = "",
    val flags: Flags,
    val flag: String? = "",
    val capital: String? = "",
    val population: Long?,
    val region: String? = "",
    @SerializedName("currencies")
    val currency: List<Currency>?,
    val area: String? = "",
    @SerializedName("alpha2Code")
    val code:String? = "",
    val nativeName: String? = ""
) : Parcelable
