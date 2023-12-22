package com.lcabral.countryapi.data.source

import com.lcabral.countryapi.data.model.Country

interface CountryDataSource {
    suspend fun getAllCountries(): List<Country>
}
