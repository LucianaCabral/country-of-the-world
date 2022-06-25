package com.lcabral.countryapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lcabral.countryapi.repository.CountryRepository

class CountryViewModelFactory(private val repository: CountryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CountryViewModel::class.java)) {
            CountryViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("viewModel Not found")
        }
    }
}