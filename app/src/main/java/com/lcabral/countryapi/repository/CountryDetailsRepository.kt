package com.lcabral.countryapi.repository

import com.lcabral.countryapi.source.CountryDetailsDataSource

class CountryDetailsRepository(private val countryDetailsDataSource: CountryDetailsDataSource) {
     suspend fun getCountryDetails() = countryDetailsDataSource.getDetailsCountries()
    }