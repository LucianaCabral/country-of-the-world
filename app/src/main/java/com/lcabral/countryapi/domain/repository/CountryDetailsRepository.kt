package com.lcabral.countryapi.domain.repository

import com.lcabral.countryapi.data.source.CountryDetailsDataSource

class CountryDetailsRepository(private val countryDetailsDataSource: CountryDetailsDataSource) {
     suspend fun getCountryDetails() = countryDetailsDataSource.getDetailsCountries()
    }