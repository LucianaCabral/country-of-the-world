package com.lcabral.countryapi.source

import com.lcabral.countryapi.model.Country

interface CountryDetailsDataSource {
    suspend fun getDetailsCountries(): List<Country>
}