package com.lcabral.countryapi.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.TestCase
import org.junit.Rule
import org.junit.Test

class CountryViewModelTest : TestCase() {

   @get:Rule
   val rule = InstantTaskExecutorRule()
    private  lateinit var viewModel: CountryViewModel

    @Test
    fun `when viewmodel getCountry get sucess then sets Country LiveData`() {
        viewModel = CountryViewModel()
    }

}