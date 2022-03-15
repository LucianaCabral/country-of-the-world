package com.lcabral.countryapi.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.lcabral.countryapi.R
import com.lcabral.countryapi.databinding.ActivityCountryDetailBinding
import com.lcabral.countryapi.model.Country
import com.lcabral.countryapi.viewmodel.CountryDetailViewModel

class CountryDetailActivity : AppCompatActivity()

{
    private lateinit var binding: ActivityCountryDetailBinding
    private lateinit var viewModel: CountryDetailViewModel

    private var country: Country? = null

    companion object {
        const val EXTRA_COUNTRY: String = "EXTRA_COUNTRY"
        const val EXTRA_FLAGS: String = "EXTRA_FLAGS"
        const val EXTRA_NAME: String = "EXTRA_NAME"
        const val EXTRA_CAPITAL: String = "EXTRA_CAPITAL"
        const val EXTRA_FLAG: String = "EXTRA_FLAG"
        const val EXTRA_POPULATION: String = "EXTRA_POPULATION"
        const val EXTRA_REGION: String = "EXTRA_REGION"
        const val EXTRA_CURRENCY_NAM: String = "EXTRA_CURRENCY_NAME"
        const val EXTRA_AREA: String = "EXTRA_AREA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        title = getString(R.string.information_of_the_country)

        getExtras()
        initUI()
        setupViewModel()
        setupObservers()
        this.setupBackButton()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(CountryDetailViewModel::class.java)
        viewModel.init()
    }

    private fun setupObservers() {
        viewModel.fetchCountryDetails()
        viewModel.itemDetails.observe(this, Observer { countriesDetails ->
            countriesDetails?.let {
            }
        })
    }

    private fun getExtras() {
        country = intent.getParcelableExtra(EXTRA_COUNTRY)
    }

    private fun initViews() {
        with(binding) {
            labelFlag.text.toString()
            labelCapital.text.toString()
            labelPopulation.text.toString()
            labelRegion.text.toString()
            labelCurrency.text.toString()
            labelArea.text.toString()
        }
    }

    private fun initUI() {
        country?.let {
            Glide.with(binding.imgFlag.context).load(country?.flags?.png).into(binding.imgFlag)
            with(binding) {
                initViews()
                countryFlag.text = country?.flag
                countryName.text = country?.name
                capital.text = country?.capital
                population.text = country?.population.toString().format("h/²")
                region.text = country?.region
                currency.text = country?.currency?.name.toString()
                area.text = country?.area.toString()
            }
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

