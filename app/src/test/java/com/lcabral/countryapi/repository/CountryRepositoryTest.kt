package com.lcabral.countryapi.repository

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.lcabral.countryapi.data.model.Country
import com.lcabral.countryapi.data.source.CountryDataSource
import com.lcabral.countryapi.domain.repository.CountryRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CountryRepositoryTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val dataSource: CountryDataSource = mockk(relaxed = true)
    private val countryRepository = CountryRepository(dataSource)
    private val mockList: List<Country> = listOf(
        mockk(relaxed = true),
        mockk(relaxed = true),
        mockk(relaxed = true)
    )

    @Before
    fun setUp() {
        Dispatchers.IO
    }

    @OptIn(DelicateCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.shutdown()
    }

    @Test
    fun ` getFromDataSourceCountries catch`(): Unit = runBlocking {
        // Given
        coEvery { dataSource.getAllCountries() } returns mockList

        // When
        countryRepository.getFromDataSourceCountries()

        coVerify { dataSource.getAllCountries() }
    }

    @Test
    fun `when the api doesnt return anything then get values form database`() = runBlocking {
        // Given
        coEvery { countryRepository.getFromDataSourceCountries() } returns emptyList()

        // When
        dataSource.getAllCountries()

        // Then
        coVerify(exactly = 1) { countryRepository.getFromDataSourceCountries() }
    }

    @Test
    fun `when the api return something then get values from database`() = runBlocking {
        // Given

        coEvery { countryRepository.getFromDataSourceCountries() } returns mockList

        //When
        val response = dataSource.getAllCountries()

        // Then
        coVerify(exactly = 1) { countryRepository.getFromDataSourceCountries() }
        assert(response == mockList)
    }
}