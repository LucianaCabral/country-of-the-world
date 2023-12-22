package com.lcabral.countryapi.presentation.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.lcabral.countryapi.data.model.Country
import com.lcabral.countryapi.domain.usecase.CountryUseCase
import com.lcabral.countryapi.viewmodel.CountryViewModel
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.*
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CountryViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    @RelaxedMockK
    private var countryUseCase: CountryUseCase = mockk(relaxed = true)
    private val countryObserver: Observer<List<Country>> = mockk(relaxed = true)
    private lateinit var viewModel: CountryViewModel

    private val mockList: List<Country> = listOf(
        mockk(relaxed = true),
        mockk(relaxed = true),
        mockk(relaxed = true)
    )

    @get:Rule
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = CountryViewModel(countryUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    fun onAfter() {
        Dispatchers.resetMain()
    }

    private fun instantiateViewModel(): CountryViewModel {
        viewModel = CountryViewModel(countryUseCase)
        countryObserver.onChanged(mockList)
        return viewModel
    }

    @Test
    fun `init Should init fetch countries`(): Unit = runBlocking {

        // GIVEN
        coEvery { countryUseCase() } returns mockList

        //WHEN
        instantiateViewModel().init()

        //THEN
        coVerify { countryUseCase.invoke() }
    }

    @Test
    fun `country Should advice observes`() = runBlocking {
        // Given
        clearAllMocks()
        coEvery { countryUseCase() } coAnswers {
            delay(1000)
            mockList
        }
        //WHEN
        instantiateViewModel().init()

        //THEN
        testDispatcher.scheduler.advanceTimeBy(1000)
        coVerify { countryObserver.onChanged(mockList) }
    }

    @Test
    fun `when countryRepository return a country set on then livedata`() = runTest {

        // Given
        coEvery { countryUseCase.invoke() } returns mockList

        // When
       instantiateViewModel().init()

        //Then
        coVerify { instantiateViewModel().countries.value }
    }
}
