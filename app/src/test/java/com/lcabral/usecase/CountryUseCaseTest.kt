package com.lcabral.usecase

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.lcabral.countryapi.model.Country
import com.lcabral.countryapi.repository.CountryRepository
import com.lcabral.countryapi.usecase.CountryUseCase
import com.nhaarman.mockitokotlin2.verify
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CountryUseCaseTest {

    private val countryRepository: CountryRepository = mockk(relaxed = true)
    private lateinit var getCountryUseCase : CountryUseCase
    private val mockList: List<Country> = listOf(
        mockk(relaxed = true),
        mockk(relaxed = true),
        mockk(relaxed = true)
    )

    @get:Rule
    val rule : InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getCountryUseCase = CountryUseCase(countryRepository)
    }

    @Test
    fun `CountryUsecase Should returns a list of country When Repository returns success`(): Unit =
        runBlocking {
            coEvery {countryRepository.getFromDataSourceCountries() } returns mockList

            getCountryUseCase.invoke()
            //Then
            verify(countryRepository).getFromDataSourceCountries()
        }

    @Test
    fun `when  The Api doesnt return anything then get values from database`() = runBlocking {
        // Given
        coEvery { countryRepository.getFromDataSourceCountries() } returns emptyList()

        // When
        getCountryUseCase()

        // Then
        coVerify(exactly = 1) { countryRepository.getFromDataSourceCountries() }
    }

    @Test
    fun `when the Api return something then get values`() = runBlocking {
        // Given
        coEvery { countryRepository.getFromDataSourceCountries() } returns mockList

        // When
        val response = getCountryUseCase()

        // Then
        coVerify(exactly = 1) { countryRepository.getFromDataSourceCountries() }
        assert(response==mockList)
    }

}
