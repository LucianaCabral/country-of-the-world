package com.lcabral.countryapi.data

import com.lcabral.countryapi.model.Country
import retrofit2.Response
import retrofit2.http.GET

interface CountryApi {
    @GET("all")
    suspend fun all(): Response<List<Country>>
}