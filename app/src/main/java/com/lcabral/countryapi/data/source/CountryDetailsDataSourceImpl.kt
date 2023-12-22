package com.lcabral.countryapi.data.source

import android.content.ContentValues.TAG
import android.util.Log
import com.lcabral.countryapi.data.CountryService
import com.lcabral.countryapi.data.model.Country

class CountryDetailsDataSourceImpl(private val countryService: CountryService) :
    CountryDetailsDataSource {

    private val breedDetails = arrayListOf<Country>()

    override suspend fun getDetailsCountries(): List<Country> {
        val request = countryService.getCountryDetails()
        if (request.isSuccessful) {
            request.body()?.let {
                breedDetails.addAll(it)
                Log.d(TAG, request.toString())
            }
        } else {
            request.errorBody()?.let {
                Log.d(TAG, it.toString())
            }
        }
        return breedDetails
    }
}