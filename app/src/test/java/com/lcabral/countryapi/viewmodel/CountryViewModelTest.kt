package com.lcabral.countryapi.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.service.autofill.Validators.not
import android.support.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asFlow
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.nullValue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

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
        val value = countryViewModel.items
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