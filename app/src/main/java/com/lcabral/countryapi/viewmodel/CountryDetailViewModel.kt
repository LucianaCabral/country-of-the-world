package com.lcabral.countryapi.viewmodel

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcabral.countryapi.data.model.Country
import com.lcabral.countryapi.domain.repository.CountryDetailsRepository
import com.lcabral.countryapi.domain.usecase.CountryDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CountryDetailViewModel(
    private val countryDetailsUseCase: CountryDetailsUseCase
) : ViewModel() {
    private val _itemsDetails = MutableLiveData<List<Country>>()

    val itemDetails: LiveData<List<Country>>
        get() = _itemsDetails

    fun init() {
        fetchCountryDetails()
    }

    fun fetchCountryDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val request = _itemsDetails.postValue(countryDetailsUseCase.invoke())
            } catch (error: Throwable) {
                error.message
            }
        }.start()
    }
}
