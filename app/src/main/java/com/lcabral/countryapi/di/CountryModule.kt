package com.lcabral.countryapi.di

import com.lcabral.countryapi.data.CountryService
import com.lcabral.countryapi.domain.repository.CountryDetailsRepository
import com.lcabral.countryapi.domain.repository.CountryRepository
import com.lcabral.countryapi.data.source.CountryDataSourceImpl
import com.lcabral.countryapi.data.source.CountryDetailsDataSourceImpl
import com.lcabral.countryapi.domain.usecase.CountryDetailsUseCase
import com.lcabral.countryapi.domain.usecase.CountryUseCase
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
                countryDetailsDataSource = CountryDetailsDataSourceImpl(countryService= CountryService())
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