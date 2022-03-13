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

class CountryViewModel : ViewModel() {
    private val service = CountryService()
    private val _items = MutableLiveData<List<Country>>()

    val items: LiveData<List<Country>>
        get() = _items

    fun fetchCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = service.getCountries()

            if (result.isSuccessful) {
                withContext(Dispatchers.Main) {
                    _items.value = result.body()
                }
            }
        }
    }

}