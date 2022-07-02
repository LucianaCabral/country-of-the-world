package com.lcabral.countryapi.di

import com.lcabral.countryapi.data.CountryDataSourceImpl
import com.lcabral.countryapi.data.CountryService
import com.lcabral.countryapi.data.usecase.CountryUseCase
import com.lcabral.countryapi.repository.CountryDetailsRepository
import com.lcabral.countryapi.repository.CountryRepository
import com.lcabral.countryapi.viewmodel.CountryDetailViewModel
import com.lcabral.countryapi.viewmodel.CountryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val countryModule = module {
    factory {
        CountryUseCase(
            countryRepository = CountryRepository(
                countryDataSource = CountryDataSourceImpl(countryService = CountryService())
            )
        )
    }

    factory {
        CountryDetailsRepository(countryService = CountryService())
    }

    viewModel {
        CountryViewModel(
            countryUseCase = get()
        )
    }

    viewModel {
        CountryDetailViewModel(
            countryDetailsRepository = get()
        )
    }
}