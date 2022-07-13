package com.lcabral.countryapi.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.lcabral.countryapi.R
import com.lcabral.countryapi.data.CountryService
import com.lcabral.countryapi.databinding.ActivityCountryDetailBinding
import com.lcabral.countryapi.model.Country
import com.lcabral.countryapi.viewmodel.CountryDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.NumberFormat
import java.util.*

class CountryDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCountryDetailBinding
    private val countryDetailsViewModel: CountryDetailViewModel by viewModel()
    private var country: Country? = null
    private val countryService = CountryService()

    companion object {
        const val EXTRA_COUNTRY: String = "EXTRA_COUNTRY"
        const val EXTRA_FLAGS: String = "EXTRA_FLAGS"
        const val EXTRA_NAME: String = "EXTRA_NAME"
        const val EXTRA_CAPITAL: String = "EXTRA_CAPITAL"
        const val EXTRA_NATIVE_NAME: String = "EXTRA_NATIVE_NAME"
        const val EXTRA_POPULATION: String = "EXTRA_POPULATION"
        const val EXTRA_REGION: String = "EXTRA_REGION"
        const val EXTRA_CURRENCY_NAM: String = "EXTRA_CURRENCY_NAME"
        const val EXTRA_CURRENCY_CODE: String = "EXTRA_CURRENCY_CODE"
        const val EXTRA_CURRENCY_SYMBOL: String = "EXTRA_CURRENCY_SYMBOL"
        const val EXTRA_AREA: String = "EXTRA_AREA"
        const val EXTRA_CODE: String = "EXTRA_CODE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        title = getString(R.string.information_of_the_country)

        getExtras()
        initUI()
        setupObservers()
        this.setupBackButton()
    }

    override fun onResume() {
        super.onResume()
        countryDetailsViewModel.init()
    }

    private fun setupObservers() {
        countryDetailsViewModel.fetchCountryDetails()
        countryDetailsViewModel.itemDetails.observe(this) { countriesDetails ->
            countriesDetails?.let {
            }
        }
    }

    private fun getExtras() {
        country = intent.getParcelableExtra(EXTRA_COUNTRY)
    }

    @SuppressLint("SetTextI18n", "ResourceType")
    private fun initViews() {
        with(binding) {
            countryName.text = country?.name
            codeName.text = country?.code.toString()
            nativeName.text = String.format(
                resources.getString(R.string.nativeName_label), country?.nativeName
            )
            capital.text = String.format(
                resources.getString(R.string.capital_label), country?.capital
            )

            population.text = String.format(
                resources.getString(R.string.population_label),
                NumberFormat.getNumberInstance(Locale.getDefault()).format(country?.population)
            )

            region.text = String.format(resources.getString(R.string.region_label), country?.region)
            area.text = String.format(resources.getString(R.string.area_label), country?.area)

            currencyName.text = String.format(
                resources.getString(R.string.currency_label_name),
                country?.currency?.map { it.name }?.joinToString()
            )
            currencyCode.text = String.format(
                resources.getString(R.string.currency_label_code),
                country?.currency?.map { it.code}?.joinToString()
            )
            currencySymbol.text = String.format(
                resources.getString(R.string.currency_label_symbol),
                country?.currency?.map { it.symbol }?.joinToString()
            )
        }
    }

    private fun initUI() {
        country?.let {
            Glide.with(binding.imgFlag.context).load(country?.flags?.png).into(binding.imgFlag)
            initViews()
        }
    }

    private fun setupBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}

