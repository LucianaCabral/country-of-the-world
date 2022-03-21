package com.lcabral.countryapi.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lcabral.countryapi.R
import com.lcabral.countryapi.databinding.ActivityCountryBinding
import com.lcabral.countryapi.model.Country
import com.lcabral.countryapi.model.Currency
import com.lcabral.countryapi.view.CountryDetailActivity.Companion.EXTRA_AREA
import com.lcabral.countryapi.view.CountryDetailActivity.Companion.EXTRA_CAPITAL
import com.lcabral.countryapi.view.CountryDetailActivity.Companion.EXTRA_CODE
import com.lcabral.countryapi.view.CountryDetailActivity.Companion.EXTRA_CURRENCY_NAM
import com.lcabral.countryapi.view.CountryDetailActivity.Companion.EXTRA_FLAGS
import com.lcabral.countryapi.view.CountryDetailActivity.Companion.EXTRA_NAME
import com.lcabral.countryapi.view.CountryDetailActivity.Companion.EXTRA_NATIVE_NAME
import com.lcabral.countryapi.view.CountryDetailActivity.Companion.EXTRA_POPULATION
import com.lcabral.countryapi.view.CountryDetailActivity.Companion.EXTRA_REGION
import com.lcabral.countryapi.viewmodel.CountryViewModel

class CountryActivity : AppCompatActivity(), ItemClickListenerCountry {
    private lateinit var binding: ActivityCountryBinding
    private lateinit var viewModel: CountryViewModel
    private var countryAdapter = CountryAdapter(this)

    companion object {
        const val EXTRA_COUNTRY = "EXTRA_COUNTRY"
        const val TAG = "Tag"
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
        country.apply {
            intent.putExtra(EXTRA_COUNTRY, country)
            intent.putExtra(EXTRA_FLAGS, flags.svg)
            intent.putExtra(EXTRA_NAME, name)
            intent.putExtra(EXTRA_NATIVE_NAME, nativeName)
            intent.putExtra(EXTRA_CAPITAL, capital)
            intent.putExtra(EXTRA_POPULATION, population)
            intent.putExtra(EXTRA_REGION, region)
            intent.putExtra(EXTRA_CURRENCY_NAM, currency.toString())
            intent.putExtra(EXTRA_AREA, area)
            intent.putExtra(EXTRA_CODE, code)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.search_action-> {
            makeText(this, "Search item", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.save -> {
            makeText(this, "Save item", Toast.LENGTH_SHORT).show()
            true
        }

        R.id.delete -> {
            makeText(this, "deletes", Toast.LENGTH_SHORT).show()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val search = menu.findItem(R.id.search_action)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
//        searchView?.setOnQueryTextListener(this)

        return true
    }


//    override fun onQueryTextSubmit(query: String?): Boolean {
//        Log.e(TAG, "onQueryTextSubmit:$query")
//        return true
//    }
//
//    override fun onQueryTextChange(newText: String?): Boolean {
//        Log.e(TAG, "onQueryTextChange: $newText")
//        return false
//    }

}




