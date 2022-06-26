package com.lcabral.countryapi.di

import com.lcabral.countryapi.data.CountryService
import com.lcabral.countryapi.repository.CountryDetailsRepository
import com.lcabral.countryapi.repository.CountryRepository
import com.lcabral.countryapi.viewmodel.CountryDetailViewModel
import com.lcabral.countryapi.viewmodel.CountryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val countryModule = module {
    factory {
        CountryRepository(countryService = CountryService())
    }

    factory {
        CountryDetailsRepository(countryService = CountryService())
    }

    viewModel {
        CountryViewModel(
            repository = get()
        )
    }

    viewModel {
        CountryDetailViewModel(
            countryDetailsRepository = get()
        )
    }
}