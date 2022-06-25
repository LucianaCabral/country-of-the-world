package com.lcabral.countryapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcabral.countryapi.model.Country
import com.lcabral.countryapi.repository.CountryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CountryViewModel(private val repository: CountryRepository) : ViewModel() {
//    private val service = CountryService()
    private val _items = MutableLiveData<List<Country>>()

    val items: LiveData<List<Country>>
        get() = _items

    fun init() {
        fetchCountries()
    }

    fun fetchCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getAllCountries()

            if (result.isSuccessful) {
                withContext(Dispatchers.Main) {
                    _items.value = result.body()
                }
            }
        }
    }
}