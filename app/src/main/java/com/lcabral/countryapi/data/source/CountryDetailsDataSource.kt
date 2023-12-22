package com.lcabral.countryapi.data.source

import com.lcabral.countryapi.data.model.Country

interface CountryDetailsDataSource {
    suspend fun getDetailsCountries(): List<Country>
}