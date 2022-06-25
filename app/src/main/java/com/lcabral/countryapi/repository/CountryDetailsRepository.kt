package com.lcabral.countryapi.repository

import com.lcabral.countryapi.data.CountryService

class CountryDetailsRepository(private val countryService: CountryService) {
     suspend fun getCountryDetails() = countryService.getCountryDetails()
    }