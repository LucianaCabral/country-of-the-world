package com.lcabral.countryapi.domain.usecase

import com.lcabral.countryapi.domain.repository.CountryDetailsRepository

class CountryDetailsUseCase(private val countryDetailsRepository: CountryDetailsRepository) {
    suspend operator fun invoke() = countryDetailsRepository.getCountryDetails()
}