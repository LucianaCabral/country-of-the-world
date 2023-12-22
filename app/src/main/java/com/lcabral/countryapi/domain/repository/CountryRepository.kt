package com.lcabral.countryapi.domain.repository

import com.lcabral.countryapi.data.source.CountryDataSource

class CountryRepository(private val countryDataSource: CountryDataSource) {
    suspend fun getFromDataSourceCountries() = countryDataSource.getAllCountries()
}