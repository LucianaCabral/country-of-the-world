package com.lcabral.countryapi.data.usecase

import com.lcabral.countryapi.repository.CountryRepository

class CountryUseCase(private val countryRepository: CountryRepository) {
 suspend operator fun invoke() = countryRepository.getFromDataSourceCountries()
}