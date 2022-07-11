package com.lcabral.countryapi.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class CountryViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `init viewModel getCountry get success then sets Country LiveData`() = runBlocking {
        //GIVEN a fresh viewModel

         val countryViewModel = CountryViewModel(ApplicationProvider.getApplicationContext())

        //WHEN
        countryViewModel.fetchCountries()

        //THEN
        val value = countryViewModel.countries
//        assertThat()

    }


    @Test
    fun `fetchCountries Should show list of countries`() = runBlocking {
        //GIVEN
        val countryViewModel = CountryViewModel(ApplicationProvider.getApplicationContext())

        //WHEN
        countryViewModel.fetchCountries()

        //THEN

    }

}