package com.lcabral.countryapi.usecase

import com.lcabral.countryapi.repository.CountryDetailsRepository

class CountryDetailsUseCase(private val countryDetailsRepository: CountryDetailsRepository) {
    suspend operator fun invoke() = countryDetailsRepository.getCountryDetails()
}