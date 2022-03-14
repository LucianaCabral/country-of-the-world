package com.lcabral.countryapi.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lcabral.countryapi.R
import com.lcabral.countryapi.databinding.ActivityCountryBinding
import com.lcabral.countryapi.model.Country
import com.lcabral.countryapi.viewmodel.CountryViewModel

class CountryActivity : AppCompatActivity(), ItemClickListenerCountry {
    private lateinit var binding: ActivityCountryBinding
    private lateinit var viewModel: CountryViewModel
    private var countryAdapter = CountryAdapter(this)

    companion object {
        const val EXTRA_COUNTRY = "EXTRA_MOVIE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        title = getString(R.string.title_first_screen)

        initUI()
        setupViewModel()
        setupObservers()

    }

    private fun initUI() {
        binding.myReciclerview.apply {
            adapter = countryAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(CountryViewModel::class.java)
    }

    private fun setupObservers() {
        viewModel.fetchCountries()
        viewModel.items.observe(this, Observer { countries ->
            countries?.let {
//                countryAdapter.update(it)
                updateList(it)
                makeText(this, "success", LENGTH_LONG).show()
                loadingVisibility(false)
            }
        })
    }

    private fun updateList(items: List<Country>) {
        countryAdapter.updateAdapter(items)
    }

    private fun loadingVisibility(isLoading: Boolean) {
        binding.progressbar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun itemClickCountry(country: Country) {
        extras(country)
    }

    private fun extras(country: Country) {
        val intent = Intent(this, CountryDetailActivity::class.java)
        intent.putExtra(EXTRA_COUNTRY,country.name)
        intent.putExtra(EXTRA_COUNTRY,country.region)
        intent.putExtra(EXTRA_COUNTRY,country.flag)
        startActivity(intent)
    }
}