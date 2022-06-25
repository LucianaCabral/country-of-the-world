package com.lcabral.countryapi.repository

import com.lcabral.countryapi.data.CountryService

class CountryRepository(private val countryService: CountryService) {
    suspend fun getAllCountries() = countryService.getCountries()
}