package com.lcabral.countryapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcabral.countryapi.data.CountryService
import com.lcabral.countryapi.model.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CountryDetailViewModel : ViewModel() {
    private val service = CountryService()
    private val _itemsDetails = MutableLiveData<List<Country>>()

    val itemDetails: LiveData<List<Country>>
        get() = _itemsDetails

    fun init() {
        fetchCountryDetails()
    }

    fun fetchCountryDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = service.getCountryDetails()

            if (result.isSuccessful) {
                withContext(Dispatchers.Main) {
                    _itemsDetails.value = result.body()
                }
            }
        }
    }

}