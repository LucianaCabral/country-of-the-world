package com.lcabral.countryapi.repository

import com.lcabral.countryapi.source.CountryDataSource

class CountryRepository(private val countryDataSource: CountryDataSource) {
    suspend fun getFromDataSourceCountries() = countryDataSource.getAllCountries()
}