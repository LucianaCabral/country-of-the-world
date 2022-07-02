package com.lcabral.countryapi.di

import com.lcabral.countryapi.source.CountryDataSourceImpl
import com.lcabral.countryapi.data.CountryService
import com.lcabral.countryapi.usecase.CountryUseCase
import com.lcabral.countryapi.repository.CountryDetailsRepository
import com.lcabral.countryapi.repository.CountryRepository
import com.lcabral.countryapi.source.CountryDetailsDataSource
import com.lcabral.countryapi.usecase.CountryDetailsUseCase
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
        CountryDetailsUseCase(
            countryDetailsRepository = CountryDetailsRepository(
                countryService = CountryService()
            )
        )
    }

    viewModel {
        CountryViewModel(
            countryUseCase = get()
        )
    }

    viewModel {
        CountryDetailViewModel(
            countryDetailsUseCase = get()
        )
    }
}