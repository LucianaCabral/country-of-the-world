package com.lcabral.countryapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lcabral.countryapi.repository.CountryDetailsRepository

class CountryDetailsViewModelFactory(private val countryDetailsRepository: CountryDetailsRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CountryDetailViewModel::class.java)) {
            CountryDetailViewModel(this.countryDetailsRepository) as T
        } else {
            throw IllegalArgumentException("viewModel Not found")
        }
    }
}