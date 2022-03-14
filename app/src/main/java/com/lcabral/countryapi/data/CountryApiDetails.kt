package com.lcabral.countryapi.data

import com.lcabral.countryapi.model.Country
import retrofit2.Response
import retrofit2.http.GET

interface CountryApiDetails {
    @GET("all")
    fun getCountryDetail(): Response<List<Country>>
}