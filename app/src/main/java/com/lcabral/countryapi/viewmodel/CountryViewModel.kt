package com.lcabral.countryapi.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcabral.countryapi.data.usecase.CountryUseCase
import com.lcabral.countryapi.model.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountryViewModel(private val countryUseCase: CountryUseCase) : ViewModel() {
    private val _items = MutableLiveData<List<Country>>()

    val items: LiveData<List<Country>>
        get() = _items

    fun init() {
        fetchCountries()
    }

    fun fetchCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _items.postValue(countryUseCase.invoke())
            } catch (exception: Exception) {
                Log.d(TAG, exception.toString())
            }
        }.start()
    }
}