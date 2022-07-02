package com.lcabral.countryapi.source

import com.lcabral.countryapi.model.Country

interface CountryDataSource {
    suspend fun getAllCountries(): List<Country>
}
