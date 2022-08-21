package com.lcabral.countryapi.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.lcabral.countryapi.model.Country
import com.lcabral.countryapi.usecase.CountryUseCase
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.ClassCastException

@OptIn(ExperimentalCoroutinesApi::class)
internal class CountryViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CountryViewModel
    private val countryUseCase: CountryUseCase = mockk(relaxed = true)
    private val countryObserver: Observer<List<Country>> = mockk(relaxed = true)
    private val mockedList: List<Country> = listOf(
        mockk(relaxed = true),
        mockk(relaxed = true),
        mockk(relaxed = true),
        mockk(relaxed = true),
    )

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        instantiateViewModel()
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    private fun instantiateViewModel(): CountryViewModel {
        viewModel = CountryViewModel(countryUseCase)
        countryObserver.onChanged(mockedList)
        return viewModel
    }

    @Test
    fun `init viewModel getCountry get success then sets Country LiveData`() = runBlocking {
        //GIVEN a fresh viewModel
        coEvery { countryUseCase.invoke() } returns mockedList

        //WHEN
        instantiateViewModel().init()

        //THEN
        coVerify { instantiateViewModel().countries.value }
    }

    @Test
    fun `init Should init fetch countries`() = runBlocking {

        //WHEN
        instantiateViewModel()

        //THEN
        coVerify { instantiateViewModel().init() }
    }

    @Test
    fun `init Should show list of countries`() = runBlocking {

        //WHEN
        instantiateViewModel().init()

        //THEN
        coVerify { instantiateViewModel().fetchCountries() }
    }

    @Test
    fun `fetchCountries Should show list countries`() = runBlocking {
        // Given
        clearMocks(countryObserver)
        coEvery { countryUseCase.invoke() } returns mockedList

        //When
        instantiateViewModel().fetchCountries()

        //Then
        coVerify { countryUseCase.invoke() }
    }

    @Test
    fun `fetchCountries Should return empty list`() = runBlocking {
        // Given
        clearMocks(countryObserver)
        coEvery { countryUseCase.invoke() } returns emptyList()

        //When
        instantiateViewModel()

        //Then
        coVerify { instantiateViewModel().countries.value }
    }

    @Test
    fun `fetch countries Should call observer`() {
        // Given
        clearMocks(countryObserver)
        coEvery { countryUseCase.invoke() } returns mockedList

        //When
        instantiateViewModel()

        //Then
        coVerify { countryObserver.onChanged(mockedList) }
    }

    @Test
    fun `fetchCountries Should catch error When throws on error`() = runBlocking {
        // Given
        val exception = ClassCastException()

        coEvery { countryUseCase.invoke() } throws exception

        //When
        instantiateViewModel().fetchCountries()

        //Then
        coVerify { countryUseCase().invoke(exception.toString()) }
    }
}