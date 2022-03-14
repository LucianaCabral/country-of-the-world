package com.lcabral.countryapi.data

import com.lcabral.countryapi.model.Country
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountryService {
    companion object {
        const val BASE_URL = ("https://restcountries.com/v2/")
    }

    private val retrofitApi: CountryApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CountryApi::class.java)

    suspend fun getCountries(): Response<List<Country>> = retrofitApi.all()

    suspend fun getCountryDetails() : Response<List<Country>> = retrofitApi.all()
}