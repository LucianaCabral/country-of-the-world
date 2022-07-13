package com.lcabral.countryapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcabral.countryapi.model.Country
import com.lcabral.countryapi.usecase.CountryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CountryViewModel(private val countryUseCase: CountryUseCase) : ViewModel() {
    private val _countries = MutableLiveData<List<Country>>()

    val countries: LiveData<List<Country>>
        get() = _countries

    fun init() {
        fetchCountries()
    }

    fun fetchCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _countries.postValue(countryUseCase.invoke())
            } catch (error: Throwable) {
                error.message
            }
        }
    }
}