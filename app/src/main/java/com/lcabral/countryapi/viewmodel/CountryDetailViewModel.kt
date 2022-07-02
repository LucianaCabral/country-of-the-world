package com.lcabral.countryapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcabral.countryapi.model.Country
import com.lcabral.countryapi.repository.CountryDetailsRepository
import com.lcabral.countryapi.usecase.CountryDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CountryDetailViewModel(
    private val countryDetailsUseCase: CountryDetailsUseCase) : ViewModel() {
    private val _itemsDetails = MutableLiveData<List<Country>>()

    val itemDetails: LiveData<List<Country>>
        get() = _itemsDetails

    fun init() {
        fetchCountryDetails()
    }

    fun fetchCountryDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = countryDetailsUseCase.invoke()

            if (result.isSuccessful) {
                withContext(Dispatchers.Main) {
                    _itemsDetails.value = result.body()
                }
            }
        }
    }
}