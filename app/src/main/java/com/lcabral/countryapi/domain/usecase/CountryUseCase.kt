package com.lcabral.countryapi.domain.usecase

import com.lcabral.countryapi.domain.repository.CountryRepository

class CountryUseCase(private val countryRepository: CountryRepository) {
 suspend operator fun invoke() = countryRepository.getFromDataSourceCountries()
}