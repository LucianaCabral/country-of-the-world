package com.lcabral.countryapi.data

import com.lcabral.countryapi.model.Country
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CountryApi {
    @GET("all")
    suspend fun all(): Response<List<Country>>

    @GET("all?fields=name")
    suspend fun getAllCountries() : List<Country>
}