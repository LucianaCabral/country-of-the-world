package com.lcabral.countryapi.data

import android.content.ContentValues.TAG
import android.util.Log
import com.lcabral.countryapi.model.Country

class CountryDataSourceImpl(private val countryService: CountryService): CountryDataSource {

    private val breedList = arrayListOf<Country>()

    override suspend fun getAllCountries(): List<Country> {
        val request = countryService.getCountries()
        if (request.isSuccessful) {
            request.body()?.let {
                breedList.addAll(it)
                Log.d(TAG,request.toString())
            }
        } else {
            request.errorBody()?.let {
                Log.d(TAG,it.toString())
            }
        }
        return breedList
    }
}