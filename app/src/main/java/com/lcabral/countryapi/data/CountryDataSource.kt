package com.lcabral.countryapi.data

import com.lcabral.countryapi.model.Country

interface CountryDataSource {
    suspend fun getAllCountries(): List<Country>
}
